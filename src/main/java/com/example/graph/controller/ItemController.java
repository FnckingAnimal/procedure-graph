package com.example.graph.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.graph.constvalue.Code;
import com.example.graph.constvalue.HintMessage;
import com.example.graph.entity.result.ItemDTO;
import com.example.graph.entity.result.ResponseEntity;
import com.example.graph.entity.table.ImageNode;
import com.example.graph.entity.table.Item;
import com.example.graph.entity.table.Link;
import com.example.graph.entity.table.Node;
import com.example.graph.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/item")
@Slf4j
@CrossOrigin(origins = "*")
public class ItemController extends BaseController {

    @GetMapping("/getItemById/{itemId}")
    public String getItemById(@PathVariable Integer itemId) {
        ResponseEntity resp = new ResponseEntity();
        ItemDTO itemDTO = itemService.getItemById(itemId, true);
        if (Utils.isNull(itemDTO)) {
            resp.fail(HintMessage.GET_ITEM_NOT_EXIST);
            return resp.toJSONString();
        }
        return resp.success(itemDTO).toJSONString();
    }

    /*
    参数可以为空，为空则查所有
     */
    @GetMapping("/getItem")
    public String getItem(@RequestParam(value = "factoryId", required = false) Integer factoryId,
                          @RequestParam(value = "departmentId", required = false) Integer departmentId,
                          @RequestParam(value = "machineId", required = false) Integer machineId) {

        ResponseEntity resp = new ResponseEntity();
        if (Utils.isNotNull(departmentId) && Utils.isNotNull(machineId)) {
            ItemDTO itemDTO = itemService.getItemBasicInfo(departmentId, machineId);
            if (Utils.isNull(itemDTO)) {
                return resp.fail(HintMessage.GET_ITEM_NOT_EXIST).toJSONString();
            }
            return resp.success(Collections.singletonList(itemDTO)).toJSONString();
        }

        List<ItemDTO> itemDTOs = itemService.getItems(factoryId, departmentId, machineId);
        if (Utils.isEmpty(itemDTOs)) {
            return resp.fail(HintMessage.GET_ITEM_NOT_EXIST).toJSONString();
        }
        return resp.success(itemDTOs).toJSONString();
    }

    @Transactional
    @PostMapping("/createItem")
    public String createItem(@RequestBody JSONObject json) {
        ResponseEntity resp = new ResponseEntity();
        /*
        厂区，部门，节点，link，图片，图例
         */
        // check
        List<String> hintMessages = new ArrayList<>();
        Integer departmentId = json.getInteger("departmentId");
        Integer factoryId = json.getInteger("factoryId");
        Integer machineId = json.getInteger("machineId");
        if (Utils.isNull(departmentId)) {
            hintMessages.add(HintMessage.DEPARTMENT_NULL_HINT);
        }
        if (Utils.isNull(machineId)) {
            hintMessages.add(HintMessage.MACHINE_NULL_HINT);
        }
        if (Utils.isNotEmpty(hintMessages)) {
            return resp.fail(String.join(",", hintMessages)).toJSONString();
        }

        String itemDesc = json.getString("itemDesc");
        ItemDTO existItem = itemService.getItemBasicInfo(departmentId, machineId);
        if (Utils.isNotNull(existItem)) {
            return resp.fail(HintMessage.CREATE_ITEM_EXIST, existItem).toJSONString();
        }
        Item item = new Item();
        item.setItemDesc(itemDesc);
        String itemUpdateTime = json.getString("itemUpdateTime");
        if (StringUtils.isEmpty(itemUpdateTime)) {
            item.setItemUpdateDate(new Date());
        } else {
            item.setItemUpdateDate(DateUtil.parse(itemUpdateTime));
        }
        item.setMachineId(machineId);
        item.setDepartmentId(departmentId);
        Integer flag = itemService.createItem(item);
        Integer itemId = item.getItemId();


        if (Objects.equals(flag, Code.CREATE_ONE_SUCCESS)) {
            saveOrUpdateData(json, itemId);
        }

        ItemDTO itemById = itemService.getItemById(itemId, true);
        return resp.success(itemById).toJSONString();
    }

    @Transactional
    @PostMapping("/saveOrUpdateItem")
    public String updateItem(@RequestBody JSONObject json) throws InterruptedException {
        Integer itemId = json.getInteger("itemId");
        saveOrUpdateData(json, itemId);
        return new ResponseEntity().success().toJSONString();
    }

    @DeleteMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable Integer id) {
        itemService.removeById(id);
        Map<String, Object> condition = new HashMap<>();
        condition.put("item_id", id);
        imageNodeService.deleteByItemId(id);
        nodeService.removeByMap(condition);
        linkService.removeByMap(condition);
        return new ResponseEntity().success().toJSONString();
    }

    private void saveOrUpdateData(JSONObject json, Integer itemId) {

            /*
            创建节点和link
             */
        Item item = new Item();
        item.setItemId(itemId);
        item.setItemUpdateDate(new Date());
        item.setItemDesc(json.getString("itemDesc"));
        item.setMachineId(json.getInteger("machineId"));
        item.setDepartmentId(json.getInteger("departmentId"));
        item.setItemUpdateDate(new Date());
        itemService.saveOrUpdate(item);

        JSONArray nodeListJA = json.getJSONArray("nodeList");
        JSONArray linkListJA = json.getJSONArray("linkList");
        JSONArray exampleNodeListJA = json.getJSONArray("exampleNodeList");

        List<Node> nodeList = new ArrayList<>();
        List<Link> linkList = new ArrayList<>();
        List<ImageNode> imageList = new ArrayList<>();
        if (Utils.isNotEmpty(nodeListJA)) {
            for (int i = 0; i < nodeListJA.size(); i++) {
                JSONObject nodeJO = nodeListJA.getJSONObject(i);
                JSONObject metaJO = nodeJO.getJSONObject("meta");
                JSONArray imageJA = metaJO.getJSONArray("image");
                List<Integer> coordinate = nodeJO.getList("coordinate", Integer.class);
                Node node = new Node();

                node.setId(nodeJO.getString("id"));
                node.setItemId(itemId);
                node.setTypeFlag(Code.NODE);
                node.setX(coordinate.get(0));
                node.setY(coordinate.get(1));
                node.setWidth(nodeJO.getInteger("width"));
                node.setHeight(nodeJO.getInteger("height"));
                node.setLabel(metaJO.getString("label"));
                node.setName(metaJO.getString("name"));
                node.setDesc(metaJO.getString("desc"));
                node.setType(metaJO.getString("type"));
                nodeList.add(node);

                if (Utils.isNotEmpty(imageJA)) {
                    for (int j = 0; j < imageJA.size(); j++) {
                        ImageNode imageNode = new ImageNode();
                        imageNode.setUrl(imageJA.getObject(j, String.class));
                        imageNode.setNodeId(node.getId());
                        imageList.add(imageNode);
                    }
                }
            }
        }
        if (Utils.isNotEmpty(linkListJA)) {
            for (int i = 0; i < linkListJA.size(); i++) {
                Link link = new Link();
                JSONObject jo = linkListJA.getJSONObject(i);
                List<Integer> startAt = jo.getList("startAt", Integer.class);
                List<Integer> endAt = jo.getList("endAt", Integer.class);

                link.setId(jo.getString("id"));
                link.setItemId(itemId);
                link.setStartId(jo.getString("startId"));
                link.setEndId(jo.getString("endId"));
                link.setStartX(startAt.get(0));
                link.setStartY(startAt.get(1));
                link.setEndX(endAt.get(0));
                link.setEndY(endAt.get(1));
                linkList.add(link);
            }
        }
        if (Utils.isNotEmpty(exampleNodeListJA)) {
            for (int i = 0; i < exampleNodeListJA.size(); i++) {
                JSONObject nodeJO = exampleNodeListJA.getJSONObject(i);
                JSONObject metaJO = nodeJO.getJSONObject("meta");
                Node node = new Node();

                node.setId(nodeJO.getString("id"));
                node.setItemId(itemId);
                node.setTypeFlag(Code.EXAMPLE_NODE);
                node.setWidth(nodeJO.getInteger("width"));
                node.setHeight(nodeJO.getInteger("height"));
                node.setLabel(metaJO.getString("label"));
                node.setName(metaJO.getString("name"));
                node.setType(metaJO.getString("type"));
                node.setShowFlag(nodeJO.getInteger("showFlag"));
                nodeList.add(node);
            }
        }

        if (Utils.isNotNull(nodeListJA)) {
            imageNodeService.deleteByItemId(itemId);
            nodeService.deleteByItemId(itemId);

            nodeService.saveOrUpdateBatch(nodeList);
            imageNodeService.saveOrUpdateBatch(imageList);
        }
        if (Utils.isNotNull(linkListJA)) {
            linkService.deleteByItemId(itemId);
            linkService.saveOrUpdateBatch(linkList);
        }
    }

}
