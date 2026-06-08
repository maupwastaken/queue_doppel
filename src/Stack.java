public class Stack {
    private Element top;
    private Element bottom;
    private Element middle;
    private int size;

    public Stack() {
        top = null;
        bottom = null;
        middle = null;
        size = 0;
    }

    public void push(int content) {
        Element temp = new Element();
        temp.setContent(content);
        if (top != null) {
            temp.setZeiger(top);
            top.setVorgaenger(temp);
        } else {
            bottom = temp;
        }
        top = temp;
        size++;
        updateMiddle();
    }

    public void pop() {
        if (top == null) return;
        top = top.getZeiger();
        if (top != null) {
            top.setVorgaenger(null);
        } else {
            bottom = null;
        }
        size--;
        updateMiddle();
    }

    public void removeAtIndex(int index) {
        if (index < 0 || index >= size) return;

        if (index == 0) {
            pop();
            return;
        }
        if (index == size - 1) {
            bottom = bottom.getVorgaenger();
            bottom.setZeiger(null);
            size--;
            updateMiddle();
            return;
        }

        Element target;
        if (index < size / 2) {
            target = top;
            for (int i = 0; i < index; i++) {
                target = target.getZeiger();
            }
        } else {
            target = bottom;
            for (int i = size - 1; i > index; i--) {
                target = target.getVorgaenger();
            }
        }

        Element prev = target.getVorgaenger();
        Element next = target.getZeiger();
        prev.setZeiger(next);
        next.setVorgaenger(prev);
        size--;
        updateMiddle();
    }

    public int getSize() {
        return size;
    }

    public int read() {
        if (top == null) return (-1);
        return top.getContent();
    }

    public String ausgabe() {
        String s = "Stack :";
        Element lauf = top;
        while (lauf != null) {
            s = s + " " + lauf.getContent();
            lauf = lauf.getZeiger();
        }
        return s;
    }

    public void insertSorted(int content) {
        if (size == 0) {
            push(content);
            return;
        }

        // Stack top is usually considered the start of the list in ausgabe()
        // Let's check Stack.ausgabe()
        // s = s + " " + lauf.getContent(); lauf = lauf.getZeiger();
        // and push adds to top: temp.setZeiger(top); top = temp;
        // So the list is [top, ..., bottom]
        
        if (content <= top.getContent()) {
            push(content);
            return;
        }

        if (content >= bottom.getContent()) {
            Element temp = new Element();
            temp.setContent(content);
            temp.setVorgaenger(bottom);
            bottom.setZeiger(temp);
            bottom = temp;
            size++;
            updateMiddle();
            return;
        }

        Element target;
        if (content < middle.getContent()) {
            // Search between top and middle
            target = top.getZeiger();
            while (target != middle && target.getContent() < content) {
                target = target.getZeiger();
            }
        } else {
            // Search between middle and bottom
            target = middle;
            while (target != null && target.getContent() < content) {
                target = target.getZeiger();
            }
        }

        // Insert before target
        Element temp = new Element();
        temp.setContent(content);
        Element prev = target.getVorgaenger();
        
        temp.setZeiger(target);
        temp.setVorgaenger(prev);
        prev.setZeiger(temp);
        target.setVorgaenger(temp);
        
        size++;
        updateMiddle();
    }

    private void updateMiddle() {
        if (size == 0) {
            middle = null;
            return;
        }
        int midIndex = size / 2;
        middle = top;
        for (int i = 0; i < midIndex; i++) {
            middle = middle.getZeiger();
        }
    }

    public int getMiddleContent() {
        if (middle == null) return -1;
        return middle.getContent();
    }
}
