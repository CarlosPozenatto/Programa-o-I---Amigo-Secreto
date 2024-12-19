import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static Scanner read = new Scanner(System.in);
    public static int counter = 0;
    public static ArrayList<String> Participantes1 = new ArrayList<>();
    public static ArrayList<String> Participantes2 = new ArrayList<>();
    public static ArrayList<String> Sorteados1;
    public static ArrayList<String> Sorteados2;

    public static void main(String[] args) throws IOException {
        pegarParticipantes();
    }

    public static void pegarParticipantes () throws IOException {
        System.out.println("Digite os nomes dos participantes (insira 'fim' para finalizar a lista): ");

        while (true) {
            String name = read.nextLine();

            if (name.equals("fim")) {
                break;
            } else if (name.isEmpty()) {
                System.out.println("Digite um nome válido (não-vazio)");
            } else {
                Participantes1.add(name);
                Participantes2.add(name);
                counter++;
            }
        }

        if (Participantes1.size() < 2) {
            System.out.println("Precisa de pelo menos 2 participantes no sorteio");
        } else {
            NaoFofoqueiro();
        }
    }

    public static void NaoFofoqueiro() throws IOException {
        System.out.println("Digite o nome do responsável a acessar o sorteio: ");
        String NameProp = read.nextLine();
        System.out.println("Digite a senha do responsável (pode conter números ou letras): ");
        String password = read.nextLine();
        System.out.println("Deseja fazer o sorteio (sim/não)?");
        String yesOrNo = read.nextLine();
        if (yesOrNo.equals("sim") || yesOrNo.equals("Sim")) {
            Sorteio();
            System.out.println("...");
            System.out.println("...");
            System.out.println("Sorteio realizado!\n--------------------------\nDeseja criar um arquivo com os sorteados?(sim/não)?");
            String NoOrYes = read.nextLine();
            if (NoOrYes.equals("sim")) {
                armazenarSorteados();
            }

            System.out.println("Arquivo criado!\n--------------------------\nDeseja ver os sorteados?(sim/não)?");
            String NoOrYes2 = read.nextLine();

            if (NoOrYes2.equals("sim") || NoOrYes.equals("Sim")) {
                System.out.println("Digite o nome do responsável: ");
                String name = read.nextLine();
                System.out.println("Digite a senha escolhida: ");
                String TestPassword = read.nextLine();
                boolean loop = true;
                while (loop) {
                    if (name.equals(NameProp) && TestPassword.equals(password)) {
                        MostrarSorteados();
                        loop = false;
                    } else {
                        System.out.println("Senha ou usuário inválido, digite novamente.");
                    }
                }
            }
        } else {
            System.out.println("O arquivo com os usuários está salvo na pasta onde está esta classe! :)");
        }

    }

    public static void Sorteio () {
        Random random = new Random();
        Sorteados1 = new ArrayList<>(counter);
        Sorteados2 = new ArrayList<>(counter);

        for (int i = 0; i < counter; i++) {
            int sorteado1;
            int sorteado2;

            if (counter - i == 1) {
                sorteado1 = (int)(Math.random() * (1 + 1) + 0);
                sorteado2 = (int)(Math.random() * (1 + 1) + 0);
            } else {
                sorteado1 = random.nextInt(counter - i);
                sorteado2 = random.nextInt(counter - i);
            }

            if (Participantes1.size() == 1 || Participantes2.size() == 1) {
                Sorteados1.add(Participantes1.getFirst());
                Sorteados2.add(Participantes2.getFirst());

                break;
            }
            String amigo1 = Participantes1.get(sorteado1);
            String amigo2 = Participantes2.get(sorteado2);

            if (amigo1.equals(amigo2)) {
                i--;

            } else {
                Sorteados1.add(amigo1);
                Sorteados2.add(amigo2);

                Participantes1.remove(sorteado1);
                Participantes2.remove(sorteado2);
            }
        }
    }

    public static void MostrarSorteados () {
        int i;
        for (i = 0; i < counter; i++) {
            System.out.println("Par " + (i + 1) + " = " + Sorteados1.get(i) + " -> " + Sorteados2.get(i));
        }
    }

    public static void armazenarSorteados () throws IOException {
        FileWriter Sorteados = new FileWriter("d:\\Sorteados.txt");
        PrintWriter record = new PrintWriter(Sorteados);

        for (int i = 0; i < counter; i++) {
            record.printf("Par " + (i + 1) + " = " + Sorteados1.get(i) + " -> " + Sorteados2.get(i) + "\n");
        }
        Sorteados.close();
    }
}