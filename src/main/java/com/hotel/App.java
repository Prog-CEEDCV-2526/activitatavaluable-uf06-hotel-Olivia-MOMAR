package com.hotel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Gestió de reserves d'un hotel.
 */
public class App {

    // --------- CONSTANTS I VARIABLES GLOBALS ---------

    // Tipus d'habitació
    public static final String TIPUS_ESTANDARD = "Estàndard";
    public static final String TIPUS_SUITE = "Suite";
    public static final String TIPUS_DELUXE = "Deluxe";

    // Serveis addicionals
    public static final String SERVEI_ESMORZAR = "Esmorzar";
    public static final String SERVEI_GIMNAS = "Gimnàs";
    public static final String SERVEI_SPA = "Spa";
    public static final String SERVEI_PISCINA = "Piscina";

    // Capacitat inicial
    public static final int CAPACITAT_ESTANDARD = 30;
    public static final int CAPACITAT_SUITE = 20;
    public static final int CAPACITAT_DELUXE = 10;

    // IVA
    public static final float IVA = 0.21f;

    // Scanner únic
    public static Scanner sc = new Scanner(System.in);

    // HashMaps de consulta
    public static HashMap<String, Float> preusHabitacions = new HashMap<String, Float>();
    public static HashMap<String, Integer> capacitatInicial = new HashMap<String, Integer>();
    public static HashMap<String, Float> preusServeis = new HashMap<String, Float>();

    // HashMaps dinàmics
    public static HashMap<String, Integer> disponibilitatHabitacions = new HashMap<String, Integer>();
    public static HashMap<Integer, ArrayList<String>> reserves = new HashMap<Integer, ArrayList<String>>();

    // Generador de nombres aleatoris per als codis de reserva
    public static Random random = new Random();

    // --------- MÈTODE MAIN ---------

    /**
     * Mètode principal. Mostra el menú en un bucle i gestiona l'opció triada
     * fins que l'usuari decideix eixir.
     */
    public static void main(String[] args) {
        inicialitzarPreus();

        int opcio = 0;
        do {
            mostrarMenu();
            opcio = llegirEnter("Seleccione una opció: ");
            gestionarOpcio(opcio);
        } while (opcio != 6);

        System.out.println("Eixint del sistema... Gràcies per utilitzar el gestor de reserves!");
    }

    // --------- MÈTODES DEMANATS ---------

    /**
     * Configura els preus de les habitacions, serveis addicionals i
     * les capacitats inicials en els HashMaps corresponents.
     */
    public static void inicialitzarPreus() {
        // Preus habitacions
        preusHabitacions.put(TIPUS_ESTANDARD, 50f);
        preusHabitacions.put(TIPUS_SUITE, 100f);
        preusHabitacions.put(TIPUS_DELUXE, 150f);

        // Capacitats inicials
        capacitatInicial.put(TIPUS_ESTANDARD, CAPACITAT_ESTANDARD);
        capacitatInicial.put(TIPUS_SUITE, CAPACITAT_SUITE);
        capacitatInicial.put(TIPUS_DELUXE, CAPACITAT_DELUXE);

        // Disponibilitat inicial (comença igual que la capacitat)
        disponibilitatHabitacions.put(TIPUS_ESTANDARD, CAPACITAT_ESTANDARD);
        disponibilitatHabitacions.put(TIPUS_SUITE, CAPACITAT_SUITE);
        disponibilitatHabitacions.put(TIPUS_DELUXE, CAPACITAT_DELUXE);

        // Preus serveis
        preusServeis.put(SERVEI_ESMORZAR, 10f);
        preusServeis.put(SERVEI_GIMNAS, 15f);
        preusServeis.put(SERVEI_SPA, 20f);
        preusServeis.put(SERVEI_PISCINA, 25f);
    }

    /**
     * Mostra el menú principal amb les opcions disponibles per a l'usuari.
     */
    public static void mostrarMenu() {
        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Reservar una habitació");
        System.out.println("2. Alliberar una habitació");
        System.out.println("3. Consultar disponibilitat");
        System.out.println("4. Llistar reserves per tipus");
        System.out.println("5. Obtindre una reserva");
        System.out.println("6. Ixir");
    }

    /**
     * Processa l'opció seleccionada per l'usuari i crida el mètode corresponent.
     */
    public static void gestionarOpcio(int opcio) {
       //TODO: Poner switch para crear opciones
       switch (opcio) {
        case 1:
            reservarHabitacio();
            break;
        case 2:
            alliberarHabitacio();
            break;
        case 3:
            consultarDisponibilitat();
            break;
        case 4:
            obtindreReservaPerTipus();
            break;
        case 5:
            obtindreReserva();
            break;
        case 6:
            System.out.println("Eixint del sistema... Gràcies per utilitzar el gestor de reserves!");
            break;
        default: 
            System.out.println("Opció no vàlida. Torna a intentar-ho.");
            break;
       }
    }

    /**
     * Gestiona tot el procés de reserva: selecció del tipus d'habitació,
     * serveis addicionals, càlcul del preu total i generació del codi de reserva.
     */
    public static void reservarHabitacio() {
        System.out.println("\n===== RESERVAR HABITACIÓ =====");
        //TODO:
        
       int tipus;
       double calcularPreuTotal = 0;   // Así a la vez que s ehace la selección de tipo de habitación y servicios va calculando el precio.    

        do {
            // 1. Mostrar tipo de habitación y el precio que tiene para poder elegir la que quiera.

            System.out.println("Tipus d'habitacions disponibles: ");
            //OPCIÓN 1
            System.out.println("\n 1. " + TIPUS_ESTANDARD); // \n para que se ponga en otra linea con espacio
            System.out.println("    El preu es: " + preusHabitacions.get(TIPUS_ESTANDARD) + " €");
            //OPCION 2
            System.out.println("\n 2. " + TIPUS_SUITE);
            System.out.println("    El preu es: " + preusHabitacions.get(TIPUS_SUITE) + " €");
            //OPCION 3
            System.out.println("\n 3. " + TIPUS_DELUXE);
            System.out.println("    El preu es: " + preusHabitacions.get(TIPUS_DELUXE) + " €");        
            
            // 2. Ahora seleccionamos el tipo de habitación que queremos 
        
            System.out.print("\n Selecciona un tipus d'habitació (1,2 o 3): ");            
            tipus = sc.nextInt();

            if (tipus == 1){
                System.out.println("Has seleccionat la habitació tipo estàndard " + TIPUS_ESTANDARD); 
                calcularPreuTotal += preusHabitacions.get(TIPUS_ESTANDARD);               
            } else {
                if (tipus == 2){
                    System.out.println("Has seleccionat la habitació tipo suite " + TIPUS_SUITE);
                    calcularPreuTotal += preusHabitacions.get(TIPUS_SUITE);                    
                } else {
                    if (tipus == 3){
                        System.out.println("Has seleccionat la habitació tipo: " + TIPUS_DELUXE); 
                        calcularPreuTotal += preusHabitacions.get(TIPUS_DELUXE);                       
                    } else {
                        System.out.println("Tipus d'habitació no vàlid. Torna a intentar-ho.");
                    }
                } 
            }             
        } while (tipus < 1 || tipus > 3); //         

        // 3. Selecciona serveis addicionals
        int serveis;

        do {
            //3.1 Mostrar el menú con los servicios adicionales.
            System.out.println("Serveis addicionals disponibles (1,2,3 y 4): ");
            //OPCIÓN 1
            System.out.println("\n 1. " + SERVEI_ESMORZAR);
            System.out.println("    El preu es: " + preusServeis.get(SERVEI_ESMORZAR) + " €");
            //OPCION 2
            System.out.println("\n 2. " + SERVEI_GIMNAS);
            System.out.println("    El preu es: " + preusServeis.get(SERVEI_GIMNAS) + " €");
            //OPCION 3
            System.out.println("\n 3. " + SERVEI_PISCINA);
            System.out.println("    El preu es: " + preusServeis.get(SERVEI_PISCINA) + " €");
            //OPCION 4
            System.out.println("\n 4. " + SERVEI_SPA);
            System.out.println("    El preu es: " + preusServeis.get(SERVEI_SPA) + " €");

            //3.2 Seleccionar el tipo de servicios
            System.out.println("\n Selecciona els serveis addicionals que vuls afegir a la teua reserva: ");
            serveis = sc.nextInt();

            switch (serveis) {
                case 1:
                    System.out.println("Has seleccionat el servei Esmorzar");
                    calcularPreuTotal += preusServeis.get(SERVEI_ESMORZAR);
                    break;
                case 2:
                    System.out.println("Has seleccionat el servei Gimnas");
                    calcularPreuTotal += preusServeis.get(SERVEI_GIMNAS);
                    break;
                case 3:
                    System.out.println("Has seleccionat el servei Piscina");
                    calcularPreuTotal += preusServeis.get(SERVEI_PISCINA);
                    break;
                case 4:
                    System.out.println("Has seleccionat el servei Spa");
                    calcularPreuTotal += preusServeis.get(SERVEI_SPA);
                    break;
                case 5:
                    System.out.println("Gracies per seleccionar el serveis addicionals");
                    break;
                default:
                    System.out.println("Opció no valida, torna a intentar-ho");
                    break;                    
            }
        } while (serveis != 5);      
     
        // 4. Calcula preu total
              
        System.out.println("\n El preu total de la teua reserva es: " + calcularPreuTotal + " €");

        // 5. Genera codi de reserva

        int codiReservaHabitacio = random.nextInt(900) + 100;

        System.out.println("\n El teu nombre de reserva es: " + codiReservaHabitacio);
                
    }

    /**
     * Pregunta a l'usuari un tipus d'habitació en format numèric i
     * retorna el nom del tipus. 
     */
    public static String seleccionarTipusHabitacio() {
        //TODO:
        return null;
    }

    /**
     * Mostra la disponibilitat i el preu de cada tipus d'habitació,
     * demana a l'usuari un tipus i només el retorna si encara hi ha
     * habitacions disponibles. En cas contrari, retorna null.
     */
    public static String seleccionarTipusHabitacioDisponible() {
        System.out.println("\nTipus d'habitació disponibles:");
        //TODO:
        return null;
    }

    /**
     * Permet triar serveis addicionals (entre 0 i 4, sense repetir) i
     * els retorna en un ArrayList de String.
     */
    public static ArrayList<String> seleccionarServeis() {
        //TODO:

        return null;
    }

    /**
     * Calcula i retorna el cost total de la reserva, incloent l'habitació,
     * els serveis seleccionats i l'IVA.
     */
    public static float calcularPreuTotal(String tipusHabitacio, ArrayList<String> serveisSeleccionats) {
        //TODO:
        return 0;
    }

    /**
     * Genera i retorna un codi de reserva únic de tres xifres
     * (entre 100 i 999) que no estiga repetit.
     */
    public static int generarCodiReserva() {
        //TODO:
        return 0;
    }

    /**
     * Permet alliberar una habitació utilitzant el codi de reserva
     * i actualitza la disponibilitat.
     */
    public static void alliberarHabitacio() {
        System.out.println("\n===== ALLIBERAR HABITACIÓ =====");
         // TODO: Demanar codi, tornar habitació i eliminar reserva
    }

    /**
     * Mostra la disponibilitat actual de les habitacions (lliures i ocupades).
     */
    public static void consultarDisponibilitat() {
        // TODO: Mostrar lliures i ocupades
    }

    /**
     * Funció recursiva. Mostra les dades de totes les reserves
     * associades a un tipus d'habitació.
     */
    public static void llistarReservesPerTipus(int[] codis, String tipus) {
         // TODO: Implementar recursivitat
    }

    /**
     * Permet consultar els detalls d'una reserva introduint el codi.
     */
    public static void obtindreReserva() {
        System.out.println("\n===== CONSULTAR RESERVA =====");
        // TODO: Mostrar dades d'una reserva concreta
 
    }

    /**
     * Mostra totes les reserves existents per a un tipus d'habitació
     * específic.
     */
    public static void obtindreReservaPerTipus() {
        System.out.println("\n===== CONSULTAR RESERVES PER TIPUS =====");
        // TODO: Llistar reserves per tipus
    }

    /**
     * Consulta i mostra en detall la informació d'una reserva.
     */
    public static void mostrarDadesReserva(int codi) {
       // TODO: Imprimir tota la informació d'una reserva
    }

    // --------- MÈTODES AUXILIARS (PER MILLORAR LEGIBILITAT) ---------

    /**
     * Llig un enter per teclat mostrant un missatge i gestiona possibles
     * errors d'entrada.
     */
    static int llegirEnter(String missatge) {
        int valor = 0;
        boolean correcte = false;
        while (!correcte) {
                System.out.print(missatge);
                valor = sc.nextInt();
                correcte = true;
        }
        return valor;
    }

    /**
     * Mostra per pantalla informació d'un tipus d'habitació: preu i
     * habitacions disponibles.
     */
    static void mostrarInfoTipus(String tipus) {
        int disponibles = disponibilitatHabitacions.get(tipus);
        int capacitat = capacitatInicial.get(tipus);
        float preu = preusHabitacions.get(tipus);
        System.out.println("- " + tipus + " (" + disponibles + " disponibles de " + capacitat + ") - " + preu + "€");
    }

    /**
     * Mostra la disponibilitat (lliures i ocupades) d'un tipus d'habitació.
     */
    static void mostrarDisponibilitatTipus(String tipus) {
        int lliures = disponibilitatHabitacions.get(tipus);
        int capacitat = capacitatInicial.get(tipus);
        int ocupades = capacitat - lliures;

        String etiqueta = tipus;
        if (etiqueta.length() < 8) {
            etiqueta = etiqueta + "\t"; // per a quadrar la taula
        }

        System.out.println(etiqueta + "\t" + lliures + "\t" + ocupades);
    }
}
