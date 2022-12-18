package knight.arkham;

import java.util.ArrayList;

//Clase encargada del manejo de mi arbol
public class NodeTree<T> {
    private final T root;
    private NodeTree<T> parent;
    private final ArrayList<NodeTree<T>> children;

    public NodeTree(T root) {
        this.root = root;
        children = new ArrayList<>();
    }

    public NodeTree<T> addChild(T child) {

        var childNode = new NodeTree<>(child);
        childNode.parent = this;

        this.children.add(childNode);
        return childNode;
    }

    public T getRoot() {
        return root;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    public int getLevel() {
        if (this.isRoot())
            return 0;
        else
            return parent.getLevel() + 1;
    }

    @Override
    public String toString() {
        return root != null ? root.toString() : "null";
    }

}
