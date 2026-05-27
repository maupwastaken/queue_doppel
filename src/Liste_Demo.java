
/**
 * Klasse Liste_Demo
 * <p>
 * Roland Stiebel
 * version 1.0
 */

//Import

import basis.*;

public class Liste_Demo extends Fenster implements KnopfLauscher {

    //Deklaration
    private Knopf ende, stackReadKnopf, queueReadKnopf, stackDoppelKnopf, queueDoppelKnopf;
    private Knopf popKnopf, pushKnopf, enqueueKnopf, dequeueKnopf, queueRemoveKnopf, stackRemoveKnopf;
    private ZahlenFeld zahlenfeld;
    private Queue myQueue;
    private Stack myStack;
    private TextFeld queueText, stackText, queueRead, stackRead;
    private BeschriftungsFeld label, queueLabel, stackLabel;

    public Liste_Demo() {
        this.initGui();
    }

    public void initGui() {
        this.setzeGroesse(600, 500);
        this.setzeTitel("Liste_Demo");
        ende = new Knopf("Ende", 490, 460, 100, 30);
        ende.setzeKnopfLauscher(this);

        label = new BeschriftungsFeld("Queue Stack Demo", 250, 50, 580, 30);
        queueLabel = new BeschriftungsFeld("Queue ", 100, 100, 150, 30);
        stackLabel = new BeschriftungsFeld("Stack ", 400, 100, 150, 30);
        queueText = new TextFeld(100, 150, 150, 30);
        stackText = new TextFeld(400, 150, 150, 30);
        queueRead = new TextFeld(210, 200, 40, 30);
        stackRead = new TextFeld(510, 200, 40, 30);

        zahlenfeld = new ZahlenFeld(250, 300, 100, 30);
        zahlenfeld.setzeZahl(0);

        queueReadKnopf = new Knopf("Read", 100, 200, 100, 30);
        queueReadKnopf.setzeKnopfLauscher(this);
        stackReadKnopf = new Knopf("Read", 400, 200, 100, 30);
        stackReadKnopf.setzeKnopfLauscher(this);
        queueReadKnopf.setzeBenutzbar(false);
        stackReadKnopf.setzeBenutzbar(false);

        dequeueKnopf = new Knopf("dequeue", 100, 250, 100, 30);
        dequeueKnopf.setzeKnopfLauscher(this);
        enqueueKnopf = new Knopf("enqueue", 100, 300, 100, 30);
        enqueueKnopf.setzeKnopfLauscher(this);
        queueDoppelKnopf = new Knopf("doppel", 100, 350, 100, 30);
        queueDoppelKnopf.setzeKnopfLauscher(this);
        popKnopf = new Knopf("pop", 400, 250, 100, 30);
        popKnopf.setzeKnopfLauscher(this);
        pushKnopf = new Knopf("push", 400, 300, 100, 30);
        pushKnopf.setzeKnopfLauscher(this);
        stackDoppelKnopf = new Knopf("doppel", 400, 350, 100, 30);
        stackDoppelKnopf.setzeKnopfLauscher(this);

        queueRemoveKnopf = new Knopf("remove idx", 100, 400, 100, 30);
        queueRemoveKnopf.setzeKnopfLauscher(this);
        stackRemoveKnopf = new Knopf("remove idx", 400, 400, 100, 30);
        stackRemoveKnopf.setzeKnopfLauscher(this);

        myQueue = new Queue();
        myStack = new Stack();
    }

    public void anzeige() {
        stackText.setzeText(myStack.ausgabe());
        queueText.setzeText(myQueue.ausgabe());
    }

    @Override
    public void bearbeiteKnopfDruck(Knopf k) {
        if (k == ende) {
            this.gibFrei();
        }
        if (k == stackReadKnopf) {
            stackRead.setzeText("" + myStack.read());
        }
        if (k == queueReadKnopf) {
            queueRead.setzeText("" + myQueue.read());
        }
        if (k == popKnopf) {
            myStack.pop();
        }
        if (k == pushKnopf) {
            myStack.push(zahlenfeld.ganzZahl());
        }
        if (k == stackDoppelKnopf) {
            Stack s2;
            s2 = new Stack();
            while (myStack.read() != -1) {
                s2.push(myStack.read());
                myStack.pop();
            }
            while (s2.read() != -1) {
                myStack.push(s2.read());
                myStack.push(s2.read());
                s2.pop();
            }

        }
        if (k == enqueueKnopf) {
            myQueue.enqueue(zahlenfeld.ganzZahl());
        }
        if (k == dequeueKnopf) {
            myQueue.dequeue();
        }
        if (k == queueDoppelKnopf) {
            Queue q2;
            q2 = new Queue();
            while (myQueue.read() != -1) {
                q2.enqueue(myQueue.read());
                myQueue.dequeue();
            }
            while (q2.read() != -1) {
                myQueue.enqueue(q2.read());
                myQueue.enqueue(q2.read());
                q2.dequeue();
            }

        }
        if (k == queueRemoveKnopf) {
            myQueue.removeAtIndex(zahlenfeld.ganzZahl());
        }
        if (k == stackRemoveKnopf) {
            myStack.removeAtIndex(zahlenfeld.ganzZahl());
        }
        this.anzeige();
        stackRead.setzeText("" + myStack.read());
        queueRead.setzeText("" + myQueue.read());
    }

}
