public class Queue {
    private Element first;
    private Element last;
    private Element middle;
    private int size;

    public Queue() {
        first = null;
        last = null;
        middle = null;
        size = 0;
    }

    public void enqueue(int content) {
        Element temp = new Element();
        temp.setContent(content);
        if (last == null) {
            first = temp;
            last = temp;
            size = 1;
            updateMiddle();
            return;
        }
        temp.setVorgaenger(last);
        last.setZeiger(temp);
        last = temp;
        size++;
        updateMiddle();
    }

    public void dequeue() {
        if (first == null) return;
        if (first == last) {
            first = null;
            last = null;
            size = 0;
            updateMiddle();
            return;
        }
        first = first.getZeiger();
        first.setVorgaenger(null);
        size--;
        updateMiddle();
    }

    public void removeAtIndex(int index) {
        if (index < 0 || index >= size) return;

        if (index == 0) {
            dequeue();
            return;
        }
        if (index == size - 1) {
            last = last.getVorgaenger();
            last.setZeiger(null);
            size--;
            updateMiddle();
            return;
        }

        Element target;
        if (index < size / 2) {
            target = first;
            for (int i = 0; i < index; i++) {
                target = target.getZeiger();
            }
        } else {
            target = last;
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
        if (first == null) return (-1);
        return first.getContent();
    }

    public String ausgabe() {
        String s = "Queue :";
        Element lauf = first;
        while (lauf != null) {
            s = s + " " + lauf.getContent();
            lauf = lauf.getZeiger();
        }
        return s;
    }

    public void insertSorted(int content) {
        if (size == 0) {
            enqueue(content);
            return;
        }

        if (content <= first.getContent()) {
            Element temp = new Element();
            temp.setContent(content);
            temp.setZeiger(first);
            first.setVorgaenger(temp);
            first = temp;
            size++;
            updateMiddle();
            return;
        }

        if (content >= last.getContent()) {
            enqueue(content);
            return;
        }

        Element target;
        if (content < middle.getContent()) {
            // Search between first and middle
            target = first.getZeiger();
            while (target != middle && target.getContent() < content) {
                target = target.getZeiger();
            }
        } else {
            // Search between middle and last
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
        middle = first;
        for (int i = 0; i < midIndex; i++) {
            middle = middle.getZeiger();
        }
    }

    public int getMiddleContent() {
        if (middle == null) return -1;
        return middle.getContent();
    }
}
