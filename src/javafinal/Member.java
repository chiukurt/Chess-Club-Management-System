/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafinal;

/*
FILE NAME: Member.java
 Pramesh Khadka 101080018 
Kalvin Balasingam 101128371
Kurt Chiu 101190261
 */
public class Member {

    private int id;
    private String fName;
    private String lName;
    private int numGames;
    private int numWins;
    private int numLoss;

    public Member(int id, String fName, String lName) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        numGames = 0;
        numWins = 0;
        numLoss = 0;
    }

    public int getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public int getNumGames() {
        return numGames;
    }

    public int getNumWins() {
        return numWins;
    }

    public int getNumLoss() {
        return numLoss;
    }

    public double getWinRate() {
        if (numGames > 0) {
            double out = (double) numWins / (double) numGames;
            return Math.round(out * 10000.0)/100.0;
        } else {
            return 0;
        }
    }

    public void addWin() {
        numWins++;
        numGames++;
    }

    public void addLoss() {
        numLoss++;
        numGames++;
    }

    @Override
    public String toString() {
        String s = String.format("%-6s%-15s%-15s%-5s%-5s%-5s%-5s", id, fName, lName, numWins, numLoss, numGames, getWinRate() + "%");
        return s;
    }
}
