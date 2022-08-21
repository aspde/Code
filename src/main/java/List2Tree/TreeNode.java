package List2Tree;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class TreeNode {

    private Integer id;

    private Integer parentId;

    @Builder.Default
    private List<TreeNode> childrenList = new ArrayList<>();

}
