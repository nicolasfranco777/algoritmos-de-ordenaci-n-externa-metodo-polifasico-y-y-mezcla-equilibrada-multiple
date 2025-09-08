public static void main(String[] args) {
        
        List<Integer> datos = Arrays.asList(23, 734, 178, 90, 2594, 1212, 5345, 747, 393, 536);
        System.out.println("Datos originales: " + datos);

        
        List<List<Integer>> runs = generarRuns(datos, 3);
        System.out.println("\nRuns iniciales:");
        for (List<Integer> run : runs) System.out.println(run);

        
        Queue<List<Integer>> A = new LinkedList<>();
        Queue<List<Integer>> B = new LinkedList<>();
        Queue<List<Integer>> C = new LinkedList<>();

        
        int[] dist = distribucionFibonacci(runs.size());
        for (int i = 0; i < runs.size(); i++) {
            if (i < dist[0]) A.add(runs.get(i));
            else B.add(runs.get(i));
        }

        System.out.println("\nDistribución inicial:");
        System.out.println("A: " + A);
        System.out.println("B: " + B);
        System.out.println("C: " + C);

        
        while (A.size() + B.size() + C.size() > 1) {
            
            Queue<List<Integer>> src1, src2, dest;
            if (A.isEmpty()) { src1 = B; src2 = C; dest = A; }
            else if (B.isEmpty()) { src1 = A; src2 = C; dest = B; }
            else { src1 = A; src2 = B; dest = C; }

            
            List<Integer> r1 = src1.poll();
            List<Integer> r2 = src2.poll();
            if (r1 != null && r2 != null) {
                List<Integer> merged = merge(r1, r2);
                dest.add(merged);
            } else if (r1 != null) dest.add(r1);
            else if (r2 != null) dest.add(r2);

            System.out.println("\nfusión:");
            System.out.println("A: " + A);
            System.out.println("B: " + B);
            System.out.println("C: " + C);
        }

        
        List<Integer> ordenado = new ArrayList<>();
        if (!A.isEmpty()) ordenado = A.poll();
        if (!B.isEmpty()) ordenado = B.poll();
        if (!C.isEmpty()) ordenado = C.poll();

        System.out.println("\nArchivo final ordenado: " + ordenado);
    }
}