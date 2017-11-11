package org.dj.bms.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dj.bms.model.Node;

public class TreeBuilder {

	@SuppressWarnings("unchecked")
	public List<Node> buildListToTree(List<Node> dirs) {
		List<Node> roots = findRoots(dirs);
		List<Node> notRoots = (List<Node>) CollectionUtils.subtract(dirs, roots);
		for (Node root : roots) {
			root.setChildren(findChildren(root, notRoots));
		}
		return roots;
	}

	public List<Node> findRoots(List<Node> allNodes) {
		List<Node> results = new ArrayList<Node>();
		for (Node node : allNodes) {
			boolean isRoot = true;
			for (Node comparedOne : allNodes) {
				if (node.getParentId() == comparedOne.getId()) {
					isRoot = false;
					break;
				}
			}
			if (isRoot) {
				node.setLevel(0);
				results.add(node);
				node.setRootId(node.getId());
			}
		}
		return results;
	}

	@SuppressWarnings("unchecked")
	private List<Node> findChildren(Node root, List<Node> allNodes) {
		List<Node> children = new ArrayList<Node>();

		for (Node comparedOne : allNodes) {
			if (comparedOne.getParentId() == root.getId()) {
				comparedOne.setParent(root);
				comparedOne.setLevel(root.getLevel() + 1);
				children.add(comparedOne);
			}
		}
		List<Node> notChildren = (List<Node>) CollectionUtils.subtract(allNodes, children);
		for (Node child : children) {
			List<Node> tmpChildren = findChildren(child, notChildren);
			if (tmpChildren == null || tmpChildren.size() < 1) {
				child.setLeaf(true);
			} else {
				child.setLeaf(false);
			}
			child.setChildren(tmpChildren);
		}
		return children;
	}

	public static void main(String[] args) {
		TreeBuilder tb = new TreeBuilder();
		List<Node> allNodes = new ArrayList<Node>();
		allNodes.add(new Node("1", "0", "根节点"));
		allNodes.add(new Node("2", "1", "行政区划"));
		allNodes.add(new Node("3", "2", "中国"));
		allNodes.add(new Node("4", "3", "中国 北京"));
		allNodes.add(new Node("5", "3", "中国 四川"));
		List<Node> roots = tb.buildListToTree(allNodes);
		for (Node n : roots) {
			System.out.println(n);
		}
	}

}