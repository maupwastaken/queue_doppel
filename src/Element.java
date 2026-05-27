
public class Element {
    private int content;
    private Element zeiger;
    private Element vorgaenger;

    public Element() {
        content = 0;
        zeiger = null;
        vorgaenger = null;
    }

    public int getContent() {
        return this.content;
    }

    public Element getZeiger() {
        return this.zeiger;
    }

    public Element getVorgaenger() {
        return this.vorgaenger;
    }

    public void setContent(int zahl) {
        this.content = zahl;
    }

    public void setZeiger(Element z) {
        this.zeiger = z;
    }

    public void setVorgaenger(Element v) {
        this.vorgaenger = v;
    }
}
