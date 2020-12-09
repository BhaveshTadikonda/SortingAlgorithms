package com.company;

import java.util.*;

class Process
{
    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time

    public Process(int pid, int bt, int art)
    {
        this.pid = pid;
        this.bt = bt;
        this.art = art;
    }
}

public class shortestjobfirst {
        static void WaitingTime(Process pro[], int n,
                                    int w[]) {
            int r[] = new int[n];
            for (int i = 0; i < n; i++)
                r[i] = pro[i].bt;
            int comp = 0, t = 0, min = Integer.MAX_VALUE;
            int shorte = 0, finish_time;
            boolean flag = false;
            while (comp != n) {
                for (int j = 0; j < n; j++) {
                    if ((pro[j].art <= t) &&
                            (r[j] < min) && r[j] > 0) {
                        min = r[j];
                        shorte = j;
                        flag = true;
                    }
                }
                if (flag == false) {
                    t++;
                    continue;
                }
                r[shorte]--;
                min = r[shorte];
                if (min == 0)
                    min = Integer.MAX_VALUE;
                if (r[shorte] == 0) {
                    comp++;
                    flag = false;
                    finish_time = t + 1;
                    w[shorte] = finish_time -
                            pro[shorte].bt -
                            pro[shorte].art;

                    if (w[shorte] < 0)
                        w[shorte] = 0;
                }
                t++;
            }
        }
        static void findTurnAroundTime(Process pro[], int n,
                                       int w[], int t[]) {
            for (int i = 0; i < n; i++)
                t[i] = pro[i].bt + w[i];
        }
        static void findavgTime(Process pro[], int n) {
            int w[] = new int[n], tat[] = new int[n];
            int total_w = 0, total_tat = 0;
            WaitingTime(pro, n, w);
            findTurnAroundTime(pro, n, w, tat);
            for (int i = 0; i < n; i++) {
                total_w = total_w + w[i];
                total_tat = total_tat + tat[i];
                System.out.println(" " + pro[i].pid + " "
                        + pro[i].bt + " " + w[i]
                        + " " + tat[i]);
            }
            System.out.println("Average waiting time = " +
                    (float) total_w / (float) n);
            System.out.println("Average turn around time = " +
                    (float) total_tat / (float) n);
        }
        public static void main(String[] args) {
            Process pro[] = {new Process(899,787,567),
                    new Process(899,900,8989),
                    new Process(787,797,9090),
                    new Process(488, 388, 300)};
            findavgTime(pro, pro.length);
        }
    }