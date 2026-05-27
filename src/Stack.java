
public class Stack {
    private Element top;
    private Element bottom;
    private int size;

    public Stack() {
        top = null;
        bottom = null;
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
    }

    public int getSize() {
        return size;
    }

    public int read() {
        if (top == null) return (-1); // -1 steht für FEHLER, also Stack ist leer
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
}
