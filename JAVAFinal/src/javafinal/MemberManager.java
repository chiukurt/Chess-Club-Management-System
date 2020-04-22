/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafinal;

/*
FILE NAME: MemberManager.java
 Pramesh Khadka 101080018 
Kalvin Balasingam 101128371
Kurt Chiu 101190261
 */
public class MemberManager {

    private int numMembers;
    private Member[] memberList;
    private int maxMembers;
    private static int currentUnusedMemberNo;

    public MemberManager(int maxMembers, int memberNumSeed) {
        numMembers = 0;
        this.maxMembers = maxMembers;
        currentUnusedMemberNo = memberNumSeed;
        memberList = new Member[maxMembers];
    }

    public int getNumMembers() {
        return numMembers;
    }

    public int findMember(int id) {
        for (int x = 0; x < numMembers; x++) {
            if (memberList[x].getId() == id) {
                return x;
            }
        }
        return -1;
    }

    public String memberInfo(int id) {
        int loc = findMember(id);
        if (loc != -1) {
            return memberList[loc].toString() + "\nWin Rate: " + Math.round(getWinRate(id) * 100.0) / 100.0 + "%";
        }
        return "";
    }

    public double getWinRate(int id) {
        int loc = findMember(id);
        if (loc != -1) {
            return memberList[loc].getWinRate();
        }
        return -1;
    }

    public String getBestPlayer() {
        String s = "";
        double topRate = memberList[0].getWinRate();

        for (int i = 0; i < numMembers; i++) {
            if (topRate < memberList[i].getWinRate()) {
                topRate = memberList[i].getWinRate();
            }
        }

        for (int i = 0; i < numMembers; i++) {
            if (topRate == memberList[i].getWinRate()) {
                s += memberList[i].toString() + "\n";
            }
        }
        return s;
    }

    public String getMostWins() {
        String s = "";
        double mostWins = memberList[0].getNumWins();

        for (int i = 0; i < numMembers; i++) {
            if (mostWins < memberList[i].getNumWins()) {
                mostWins = memberList[i].getNumWins();
            }
        }

        for (int i = 0; i < numMembers; i++) {
            if (mostWins == memberList[i].getNumWins()) {
                s += memberList[i].toString() + "\n";
            }
        }
        return s;
    }

    public String listMembers() {
        String s = "";
        for (int i = 0; i < numMembers; i++) {
            s += memberList[i].toString() + "\n";
        }
        return s;
    }

    public boolean addMember(String fName, String lName) {
        if (numMembers < maxMembers) {
            memberList[numMembers] = new Member(currentUnusedMemberNo, fName, lName);
            numMembers++;
            currentUnusedMemberNo++;
            return true;
        }
        return false;
    }

    public boolean deleteMember(int id) {
        int loc = findMember(id);
        if (loc != -1) {
            memberList[loc] = memberList[numMembers - 1];
            numMembers--;
            return true;
        }
        return false;
    }

    public boolean addWin(int id) {
        int loc = findMember(id);
        if (loc != -1) {
            memberList[loc].addWin();
            return true;
        }
        return false;
    }

    public boolean addLoss(int id) {
        int loc = findMember(id);
        if (loc != -1) {
            memberList[loc].addLoss();
            return true;
        }
        return false;
    }

}
