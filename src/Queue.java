
public class Queue {
    private Element first;
    private Element last;
    private int size;

    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    public void enqueue(int content) {
        Element temp = new Element();
        temp.setContent(content);
        if (last == null) {
            first = temp;
            last = temp;
            size = 1;
            return;
        }
        temp.setVorgaenger(last);
        last.setZeiger(temp);  //noch der alter last
        last = temp;   // jetzt ist last = georg, also neu
        size++;
    }

    public void dequeue() {
        if (first == null) return;
        if (first == last) {
            first = null;
            last = null;
            size = 0;
            return;
        }
        first = first.getZeiger();
        first.setVorgaenger(null);
        size--;
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
    }

    public int getSize() {
        return size;
    }

    public int read() {
        if (first == null) return (-1); // -1 steht für FEHLER, also Queue ist leer
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
}
