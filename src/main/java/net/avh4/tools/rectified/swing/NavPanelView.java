package net.avh4.tools.rectified.swing;

import com.explodingpixels.macwidgets.SourceListDarkColorScheme;
import com.explodingpixels.macwidgets.plaf.SourceListTreeUI;
import com.explodingpixels.widgets.IconProvider;
import net.avh4.framework.uilayer.swing.SwingGraphicsOperations;
import net.avh4.math.geometry.Rect;
import net.avh4.tools.rectified.NavPanel;
import net.avh4.tools.rectified.Observables;
import net.avh4.tools.rectified.model.Component;
import net.avh4.tools.rectified.model.Design;
import net.avh4.util.Observer;
import org.pcollections.ConsPStack;
import org.pcollections.PStack;
import org.pcollections.PVector;
import org.pcollections.TreePVector;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.*;
import java.util.Enumeration;

public class NavPanelView extends JPanel {
    private final JTree tree;

    public NavPanelView(Observables observables, final NavPanel panel) {
        super(new BorderLayout());
        tree = new JTree() {
            @Override protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                super.paintComponent(g);
            }
        };
        final SourceListTreeUI treeUI = new SourceListTreeUI();
        tree.setUI(treeUI);
        treeUI.setColorScheme(new SourceListDarkColorScheme());
        add(tree, BorderLayout.CENTER);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override public void valueChanged(TreeSelectionEvent e) {
                final Object node = e.getPath().getLastPathComponent();
                if (node instanceof DesignTreeNode.ComponentTreeNode) {
                    panel.actions().select(((DesignTreeNode.ComponentTreeNode) node).path);
                } else {
                    System.out.println("Nothing to do: Selected " + node + "(" + node.getClass() + ")");
                }
            }
        });

        observables.design().watch(new Observer<Design>() {
            @Override public void update(Design newValue) {
                tree.setModel(new DefaultTreeModel(new RootTreeNode(newValue)));
                tree.expandRow(0);
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
            PStack<Component> path = ConsPStack.<Component>singleton(design.mainComponent());
            final Component component = design.mainComponent().children().get(childIndex);
            return new ComponentTreeNode(component, path.plus(component), this);
        }

        @Override public int getChildCount() {
            return design.mainComponent().children().size();
        }

        @Override public TreeNode getParent() {
            return null;
        }

        @Override public int getIndex(TreeNode node) {
            return design.mainComponent().children().indexOf(((ComponentTreeNode) node).component);
        }

        @Override public boolean getAllowsChildren() {
            return true;
        }

        @Override public boolean isLeaf() {
            return false;
        }

        @Override public Enumeration children() {
            return new PVectorEnumeration<>(design.mainComponent().children());
        }

        private static class ComponentTreeNode extends DefaultMutableTreeNode {
            private final Component component;
            private final PStack<Component> path;
            private final TreeNode parent;
            private final SwingGraphicsOperations graphicsOperations = new SwingGraphicsOperations();

            public ComponentTreeNode(Component component, PStack<Component> path, TreeNode parent) {
                this.component = component;
                this.path = path;
                this.parent = parent;
            }

            @Override public String toString() {
                return component.navString();
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

            @Override public Object getUserObject() {
                return new IconProvider() {
                    @Override public Icon getIcon() {
                        return new Icon() {
                            @Override public void paintIcon(java.awt.Component c, Graphics g, int x, int y) {
                                graphicsOperations.setGraphicsContext(g);
                                component.draw(Rect.fromTopLeft(x, y, getIconWidth(), getIconHeight()), graphicsOperations, null);
                            }

                            @Override public int getIconWidth() {
                                return 16;
                            }

                            @Override public int getIconHeight() {
                                return 16;
                            }
                        };
                    }
                };
            }
        }
    }

    private static class RootTreeNode implements TreeNode {
        private Design design;

        public RootTreeNode(Design newValue) {
            design = newValue;
        }

        @Override public TreeNode getChildAt(int childIndex) {
            return new DesignTreeNode(design);
        }

        @Override public int getChildCount() {
            return 1;
        }

        @Override public TreeNode getParent() {
            return null;
        }

        @Override public int getIndex(TreeNode node) {
            return 0;
        }

        @Override public boolean getAllowsChildren() {
            return true;
        }

        @Override public boolean isLeaf() {
            return false;
        }

        @Override public Enumeration children() {
            return new PVectorEnumeration<>(TreePVector.singleton(design));
        }
    }

    private static class PVectorEnumeration<T> implements Enumeration {
        private PVector<T> components;

        public PVectorEnumeration(PVector<T> components) {
            this.components = components;
        }

        @Override public boolean hasMoreElements() {
            return !components.isEmpty();
        }

        @Override public Object nextElement() {
            final T component = components.get(0);
            components = components.minus(0);
            return component;
        }
    }
}
