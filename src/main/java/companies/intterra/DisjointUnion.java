package companies.intterra;

import java.util.Arrays;

public class DisjointUnion {

    int[] depth, parent;
    int maxDepth;

    public DisjointUnion(int maxDepth) {
        depth = new int[maxDepth];
        parent = new int[maxDepth];
        this.maxDepth = maxDepth;
        make();
    }

    void make() {
        for (int i = 0; i < maxDepth; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot)
            return;

        if (depth[xRoot] < depth[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (depth[yRoot] < depth[xRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            depth[xRoot]++;
        }
    }

    public int[] getDepth() {
        return depth;
    }

    public int[] getParent() {
        return parent;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DisjointUnionSet{");
        sb.append("depth=").append(Arrays.toString(depth));
        sb.append(", parent=").append(Arrays.toString(parent));
        sb.append(", maxDepth=").append(maxDepth);
        sb.append('}');
        return sb.toString();
    }
}
