package net.avh4.tools.rectified.swing;

import net.avh4.tools.rectified.NavPanel;
import net.avh4.tools.rectified.Observables;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Design;
import net.avh4.util.Observer;
import org.pcollections.PVector;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.Enumeration;

public class NavPanelView extends JPanel {
    private final JTree tree;

    public NavPanelView(Observables observables, final NavPanel panel) {
        super(new BorderLayout());
        tree = new JTree();
        add(tree, BorderLayout.CENTER);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override public void valueChanged(TreeSelectionEvent e) {
                final Object node = e.getPath().getLastPathComponent();
                if (node instanceof DesignTreeNode.ComponentTreeNode) {
                    panel.actions().select(((DesignTreeNode.ComponentTreeNode) node).component);
                } else {
                    System.out.println("Nothing to do: Selected " + node + "(" + node.getClass() + ")");
                }
            }
        });

        observables.design().watch(new Observer<Design>() {
            @Override public void update(Design newValue) {
                tree.setModel(new DefaultTreeModel(new DesignTreeNode(newValue)));
            }
        });
    }

    @Override public Dimension getPreferredSize() {
        return new Dimension(300, 500);
    }


    private static class DesignTreeNode implements TreeNode {
        private final Design design;

        private DesignTreeNode(Design design) {
            this.design = design;
        }

        @Override public String toString() {
            return "Design";
        }

        @Override public TreeNode getChildAt(int childIndex) {
            return new ComponentTreeNode(design.components().get(childIndex), this);
        }

        @Override public int getChildCount() {
            return design.components().size();
        }

        @Override public TreeNode getParent() {
            return null;
        }

        @Override public int getIndex(TreeNode node) {
            return design.components().indexOf(((ComponentTreeNode) node).component);
        }

        @Override public boolean getAllowsChildren() {
            return true;
        }

        @Override public boolean isLeaf() {
            return false;
        }

        @Override public Enumeration children() {
            return new ComponentsEnumeration(design.components());
        }

        private static class ComponentTreeNode implements TreeNode {
            private final Component component;
            private final TreeNode parent;

            public ComponentTreeNode(Component component, TreeNode parent) {
                this.component = component;
                this.parent = parent;
            }

            @Override public String toString() {
                return component.toString();
            }

            @Override public TreeNode getChildAt(int childIndex) {
                return null;
            }

            @Override public int getChildCount() {
                return 0;
            }

            @Override public TreeNode getParent() {
                return parent;
            }

            @Override public int getIndex(TreeNode node) {
                return 0;
            }

            @Override public boolean getAllowsChildren() {
                return false;
            }

            @Override public boolean isLeaf() {
                return true;
            }

            @Override public Enumeration children() {
                return null;
            }
        }

        private static class ComponentsEnumeration implements Enumeration {
            private PVector<Component> components;

            public ComponentsEnumeration(PVector<Component> components) {
                this.components = components;
            }

            @Override public boolean hasMoreElements() {
                return !components.isEmpty();
            }

            @Override public Object nextElement() {
                final Component component = components.get(0);
                components = components.minus(0);
                return component;
            }
        }
    }
}
