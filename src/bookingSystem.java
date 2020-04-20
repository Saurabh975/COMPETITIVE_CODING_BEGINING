import java.io.*;
import java.util.*;
public class bookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<SabkaHisab> cost_person = new ArrayList<>();
        ArrayList<Table> table = new ArrayList<>();
        for (int i = 1; i <= n; i++) cost_person.add(new SabkaHisab(sc.nextInt(), sc.nextInt(), i));
        int k = sc.nextInt();
        for (int i = 1; i <= k; i++) table.add(new Table(sc.nextInt(), i));
        Collections.sort(cost_person);
        Collections.sort(table);
        int kitna_kamaya = 0;
        boolean vis[] = new boolean[n];
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int jyadaMaal = 0, konAyaega = -1;
            for (int j = 0; j < n; j++) {
                if (!vis[j] && (cost_person.get(j).kitna_log <= table.get(i).table_aukaad) && (cost_person.get(j).kitna_paisa > jyadaMaal)) {

                    jyadaMaal = cost_person.get(j).kitna_paisa;
                    konAyaega = j;

                }
            }

            if (konAyaega != -1) {
                vis[konAyaega] = true;
                kitna_kamaya += cost_person.get(konAyaega).kitna_paisa;
                ans.add(cost_person.get(konAyaega).kitna_position + " " + table.get(i).table_position);
            }
        }


        System.out.println(ans.size() + " " + kitna_kamaya);
        for (int i = 0; i < ans.size(); i++) System.out.println(ans.get(i));
    }


    static class SabkaHisab implements Comparable<SabkaHisab> {
        int kitna_log, kitna_paisa, kitna_position;

        SabkaHisab(int kitna_log, int kitna_paisa, int kitna_position) {
            this.kitna_log = kitna_log;
            this.kitna_paisa = kitna_paisa;
            this.kitna_position = kitna_position;
        }

        @Override
        public int compareTo(SabkaHisab obj) {
            return this.kitna_log - obj.kitna_log;
        }
    }

    static class Table implements Comparable<Table> {
        int table_aukaad, table_position;

        Table(int table_aukaad, int table_position) {
            this.table_aukaad = table_aukaad;
            this.table_position = table_position;
        }

        @Override
        public int compareTo(Table obj) {
            return this.table_aukaad - obj.table_aukaad;
        }
    }
}
