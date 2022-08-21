package List2Tree;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class List2Tree {

    /**
     * 获取以"id"值为id的树根节点
     *
     * @param nodeList 节点集合
     * @param id       节点的id
     * @return 以"id"值为id的根节点
     */
    public TreeNode getTreeNode(List<TreeNode> nodeList, int id) {
        Map<Integer, TreeNode> map = nodeList.stream().collect(Collectors.toMap(e -> e.getId(), e -> e));
        nodeList.forEach(e -> {
            if (map.containsKey(e.getParentId())) {
                map.get(e.getParentId()).getChildrenList().add(e);
            }
        });
        return map.getOrDefault(id, TreeNode.builder().build());
    }

}
