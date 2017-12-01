package com.example.yuqinghe.a4;

import java.util.Observable;
import java.util.Random;
import java.util.ArrayList;

public class SimonModel extends Observable {
    public enum State {START, COMPUTER, HUMAN, LOSE, WIN };

    State state;
    int score;
    int length;
    int buttons;
    ArrayList <Integer> sequence = new ArrayList ();
    int index;
    int time;
    static Random rand;

    int b;
    int dl;



    public SimonModel(int _buttons) {
        length = 1;
        buttons = _buttons;
        state = State.START;
        score = 0;
        rand = new Random();
        time = 1000;
        b = 4;
        dl = 1;
    }

    static final SimonModel model = new SimonModel(4);

    public static SimonModel getInstance() {
        return model;
    }

    public int getb() {return b;}
    public int getdl() {return dl;}
    public void setb(int i) {b = i;}
    public void setdl(int i) {dl = i;}

    public void setTime(int i) {
        if (i == 0) {
            time = 2000;
        }
        else if (i == 1) {
            time = 1000;
        }
        else {
            time = 250;
        }
    }

    public int getWaitTime() {
        return  time;
    }

    public void setButtons(int b) {buttons = b;}
    public int getNumButtons() {return buttons;}
    public int getScore() {return score;}
    public State getState() {return state;}
    public String getStateAsString() {
        switch (getState()) {
            case START:
                return "START";

            case COMPUTER:
                return "COMPUTER";

            case HUMAN:
                return "HUMAN";

            case LOSE:
                return "LOSE";

            case WIN:
                return "WIN";
            default:
                return "Unkown State";
        }
    }

    int random(int min, int max) {
        return rand.nextInt(max-min+1)+min;
    }

    public void lose() {
        state = State.LOSE;
    }

    public void newRound() {
        if (state == State.LOSE) {
            length = 1;
            score = 0;
        }

        sequence.clear();

        for (int i = 0; i < length; i++) {
            int b = random(0, buttons-1);
            sequence.add(b);
        }

        index = 0;
        state = State.COMPUTER;
    }

    public int nextButton() {
        if (state != State.COMPUTER) {
            return -1;
        }

        int button = sequence.get(index);
        index++;
        if (index >= sequence.size()) {
            index = 0;
            state = State.HUMAN;
        }

        return button;
    }

    public boolean verifyButton(int button) {
        if (state != State.HUMAN) {
            return false;
        }

        // did they press the right button?
        boolean correct = (button == sequence.get(index));

        // advance to next button
        index++;

        // pushed the wrong buttons
        if (!correct) {
            state = State.LOSE;
            // they got it right
        } else {

            // if last button, then the win the round
            if (index == sequence.size()) {
                state = State.WIN;
                // update the score and increase the difficulty
                score++;
                length++;
            }
        }
        return correct;
    }

    public void initObservers() {
        setChanged();
        notifyObservers();
    }


};