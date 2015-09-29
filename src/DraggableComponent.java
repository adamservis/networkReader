



/*
 *  Copyright 2010 De Gregorio Daniele.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

//this is part of a pair of classes we adopted to use for our icons
//some modifications were made in order for it to suit our purposes
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JComponent;

public class DraggableComponent extends JComponent {
    
    /** If sets <b>TRUE</b> this component is draggable */
    private boolean draggable = true;
    /** 2D Point representing the coo mouse is, relative parentrdinate where 
     * container */
    protected Point anchorPoint;
    /** Default mouse cursor for dragging action */
    protected Cursor draggingCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    /** If sets <b>TRUE</b> when dragging component, it will be painted over each other (z-Buffer change) */
    protected boolean overbearing = false;
    
    public DraggableComponent() {
        addDragListeners();
        setOpaque(true);
        setBackground(new Color(240,240,240));
    }
    /**
     * We have to define this method because a JComponent is a void box. So we have to
     * define how it will be painted. We create a simple filled rectangle.
     *
     * @param g Graphics object as canvas
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isOpaque()) {
            g.setColor(getBackground());
            g.fillRect(0, 0, getWidth(), getHeight());
        }

    }
    /**
     * Add Mouse Motion Listener with drag function
     */
    private void addDragListeners() {
        /** This handle is a reference to THIS because in next Mouse Adapter 
         * "this" is not allowed */
        final DraggableComponent handle = this;
        addMouseMotionListener(new MouseAdapter() {

            @Override
            public void mouseMoved(MouseEvent e) {
                anchorPoint = e.getPoint();
                setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int anchorX = anchorPoint.x;
                int anchorY = anchorPoint.y;
                //Modification
                //logic to ensure that our icons stay in the bounds of the jpanel
                //without it, icons may be dragged off the screen and 'lost'
                int XMIN = -25;
                int YMIN = -25;
                int XMAX = HomeForm.JPanel1.getWidth() + XMIN;
                int YMAX = HomeForm.JPanel1.getHeight() + YMIN;
                int newX;
                int newY;
                
                Point parentOnScreen = getParent().getLocationOnScreen();
                Point mouseOnScreen = e.getLocationOnScreen();
                
                newX = mouseOnScreen.x - parentOnScreen.x - anchorX;
                newY = mouseOnScreen.y - parentOnScreen.y - anchorY;
                
                if(newX <= XMIN){
                    newX = XMIN;
                }
                if(newX >= XMAX){
                    newX = XMAX;
                }
                if(newY <= YMIN){
                    newY = YMIN;
                }
                if(newY >= YMAX){
                    newY = YMAX;
                }
                
                Point position = new Point(newX, newY);
                setLocation(position);
                
                //modification
                //this redraws the lines between our icons
                NetworkReader.connections.clearLines();
                NetworkReader.setNetworkConnections();
                HomeForm.JPanel1.repaint();
                


                //Change Z-Buffer if it is "overbearing"
                if (overbearing) {
                    getParent().setComponentZOrder(handle, 0);
                    repaint();
                }
            }
        });
    }
    //Modification
    //mouse listeners for vm icon, called at icon construction
    //they take an integer corresponding to the virtual machines index
    public void addMouseListenersVM(final int vmIndex){
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    //sets point variable in NetworkReader
                    //it is used for the created form and forms that stem from it
                    NetworkReader.p = MouseInfo.getPointerInfo().getLocation();
                    //calls the form for the corresponding network object
                    NetworkReader.vmList.get(vmIndex).form();
                    e.consume();             
                }  
            }
        });
    }
    //Modification
    //mouse listeners for vm icon, called at icon construction
    //they take an integer corresponding to the virtual machines index
    public void addMouseListenersHUB(final int hubIndex){
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    //sets point variable in NetworkReader
                    //it is used for the created form and forms that stem from it
                    NetworkReader.p = MouseInfo.getPointerInfo().getLocation();
                    //calls the form for the corresponding network object
                    NetworkReader.hubList.get(hubIndex).form();
                    e.consume();         
                }  
            }
        });
    }
    //modification
    //this is called at icon deletion
    //it fixes a bug that occurred when an object was deleted,
    //and later replaced with an object with the same index
    public void removeClickListeners() {
        for (MouseListener listener : this.getMouseListeners()) {
            removeMouseListener(listener);
        }
        setCursor(Cursor.getDefaultCursor());
    }
    /**
     * Remove all Mouse Motion Listener. Freeze component.
     */
    private void removeDragListeners() {
        for (MouseMotionListener listener : this.getMouseMotionListeners()) {
            removeMouseMotionListener(listener);
        }
        setCursor(Cursor.getDefaultCursor());
    }

    /**
     * Get the value of draggable
     *
     * @return the value of draggable
     */
    public boolean isDraggable() {
        return draggable;
    }

    /**
     * Set the value of draggable
     *
     * @param draggable new value of draggable
     */
    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
        if (draggable) {
            addDragListeners();
        } else {
            removeDragListeners();
        }

    }

    /**
     * Get the value of draggingCursor
     *
     * @return the value of draggingCursor
     */
    public Cursor getDraggingCursor() {
        return draggingCursor;
    }

    /**
     * Set the value of draggingCursor
     *
     * @param draggingCursor new value of draggingCursor
     */
    public void setDraggingCursor(Cursor draggingCursor) {
        this.draggingCursor = draggingCursor;
    }

    /**
     * Get the value of overbearing
     *
     * @return the value of overbearing
     */
    public boolean isOverbearing() {
        return overbearing;
    }

    /**
     * Set the value of overbearing
     *
     * @param overbearing new value of overbearing
     */
    public void setOverbearing(boolean overbearing) {
        this.overbearing = overbearing;
    }
}