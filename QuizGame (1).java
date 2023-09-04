import java.util.Scanner;
import java.util.Random;
class QuizLoginMethods
{
        String userName,password;
        final int CAPTCHA_LENGTH = 6;
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String captchaText;
        Scanner sc=new Scanner(System.in);
        void login() {
        System.out.println();
        System.out.print("Enter Your UserName: ");
        userName=sc.nextLine();
        System.out.println();
        System.out.println("Here Some Rules For Creating Password");
        passwordRules();
        System.out.print("Enter Your Password: ");
        password=sc.nextLine();

        while(true){
            boolean flag=checkPassword(password);
            if(!flag){
                System.out.print("Sorry! Your Password Is Not Valid.\nEnter Valid Password: ");
                password=sc.nextLine();
            }
            else{
                System.out.println("Ok!! " + userName + " Your password is Set\n");
                System.out.println();
                System.out.print("Remember Your Password!! This Will Help You To Enter In Quiz Game");
                System.out.println();
                break;
            }
        }
        System.out.println();
        System.out.println("CAPTCHA: "+generateCaptcha());
    }
        void passwordRules(){
        System.out.println("-->1. Length: Passwords should be at least 8 characters long. ");
        System.out.println("-->2. Passwords should include a mix of upper and lowercase letters.");
        System.out.println("-->3. Passwords should include a numbers. and special characters. ");
        System.out.println("-->4. Passwords should include a special characters.");
        System.out.println();
    }

        boolean checkPassword(String password){
        if(password.length()<8){
            return false;
        }
        boolean hasSpecialChar=false;
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        for(int i=0;i<password.length();i++){
            char c=password.charAt(i);
            if(Character.isUpperCase(c)){
                hasUppercase=true;
            }
            else if(Character.isLowerCase(c)){
                hasLowercase=true;
            }
            else if(Character.isDigit(c)){
                hasDigit=true;
            }
            else if(password.contains("#")|| password.contains("!") || password.contains("@") || password.contains("$")|| password.contains("%") || password.contains("^")
                    || password.contains("&")|| password.contains("*"))
            {
                hasSpecialChar=true;
            }
        }
        return (hasUppercase&&hasLowercase&&hasDigit&&hasSpecialChar);
    }
        String generateCaptcha(){
        StringBuffer captchaBuffer = new StringBuffer();
        Random random = new Random();

        // Generate captcha text
        for (int i = 0; i < CAPTCHA_LENGTH; i++) {
            captchaBuffer.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        captchaText = captchaBuffer.toString();
        return captchaText;
    }
        void validCaptcha(){
        while(true){
            System.out.print("Enter Captcha: ");
            String captcha=sc.nextLine();
            if(captcha.equals(captchaText)){
                System.out.println("-------------------------------------------------");
                System.out.println("|               LOGIN SUCCESSFULLY              |");
                System.out.println("-------------------------------------------------");
                System.out.println();
                break;
            }
            else {
                System.out.println("Your Captcha Is Wrong!");
                System.out.println("Enter Valid Captcha");
                System.out.println();
                System.out.println("CAPTCHA: "+generateCaptcha());
                System.out.println();
            }
        }
    }
        void quizGameInstructionsAndRules(){
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("|                INSTRUCTIONS                  |");
        System.out.println("------------------------------------------------");
        System.out.println();
        System.out.println("-->1. Firstly To Play Quiz Game Please Remember Your Password Which Are You Generated At Login ");
        System.out.println("-->2. There Are 5 Types Of Quiz Available, Which Are\n	1.General Knowledge Quiz\n	2. History Quiz\n	3. Sports Quiz\n	4. Java Quiz\n	5. Logical Quiz");
        System.out.println("-->3. Ten questions for each Quiz.");
        System.out.println("-->4. You have four options for each question.");
        System.out.println("-->5. Total Score forEach Quiz is 50.");
        System.out.println("-->6. You Give Answer In Numeric Form, For Example If Answer is Option (A) You Enter 1.");
        System.out.println("-->7. You have three lifelines available:-\n    1. 50-50(Two incorrect options will be removed)\n    2. Exchange Questions\n    3. Expert Advise.");
        System.out.println("-->8. You Can Select Any Type Of Quiz After End Of Attempted Quiz And Select Any Particular Quiz Only One Time.");
        System.out.println("-->9. You Spend All Lifeline In One Question.");
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("|                    RULES                     |");
        System.out.println("------------------------------------------------");
        System.out.println();
        System.out.println("-->1. You Can Use Three Lifelines In All Quiz.");
        System.out.println("-->2. It Means That In All Indivisual Type Of Quiz You Have 3 Lifeline.");
        System.out.println("-->3. You Spend All Other Lifeline In 50-50 And Exchange Question Lifeline But You Are Not Able To Spend Any Other Lifeline In Expert Advise Lifeline. ");
    }
        void validPassword(){
        System.out.print("Enter Your Password Which You Create At Login For Playing Quiz Game: ");
        String password1 = sc.nextLine();
        while(true) {
            if (password1.equals(password)) {
                System.out.println("Your Password Is Right!!");
                break;
            }
            else {
                System.out.print("Enter Valid PassWord: ");
                password1 = sc.nextLine();
            }
        }
    }
}

class QuizMain
{
    int lifelinesUsed;
    int score;
    int i;
    boolean lifeline50_50 = false;
    boolean lifelineExchangeQuestion = false;
    boolean lifelineExpertAdvise = false;
    static boolean playGeneral=false;
    static boolean playHistory=false;
    static boolean playSports=false;
    static boolean playJava=false;
    static boolean playLogical=false;
    Scanner sc=new Scanner(System.in);

    void setLifelinesUsed(int lifelinesUsed,int score){
        this.score=score;
        this.lifelinesUsed=lifelinesUsed;
    }

    void printQuestions(String []questions,String []options,String []answers,String []options50_50,String exchangequestion,String exchangeoption,String exchange50_50option,String exchangequeanswer,int quizi){
        this.i=quizi;
        for (i = 0; i < 10; i++) {
            System.out.println(questions[i]);
            System.out.println(options[i]);
            checkAnswers(questions,options,answers,i, options50_50,exchangequestion,exchangeoption,exchange50_50option,exchangequeanswer);
            System.out.println();
        }
    }
    void checkAnswers(String []questions,String []options,String []answers,int i,String []options50_50,String exchangequestion,String exchangeoption,String exchange50_50option,String exchangequeanswer){
        System.out.println();
        while(true) {
            if (lifelinesUsed == 3) {
                System.out.println();
                System.out.println("YOU HAVE NO LIFELINE!!");
                System.out.println();
                System.out.print("Enter Your Answer: ");
                String answer = sc.nextLine();
                while (true) {
                    if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4")) {
                        if (answer.equals(answers[i])) {
                            printRightWrongAnswer(true);
                            score++;
                        } else {
                            printRightWrongAnswer(false);
                        }
                        break;
                    } else {
                        System.out.print("Enter Valid Choice(Between 1 To 4): ");
                        answer = sc.nextLine();
                    }
                }
                break;

            } else {
                System.out.println("If You Want To Use Lifeline Enter Yes And If You Don't Want To Use Enter No!");
                System.out.println();
                System.out.print("What's Your Choice: ");
                String takeLifeline = sc.nextLine();
                while (true) {
                    if (takeLifeline.equals("yes") || takeLifeline.equals("YES") || takeLifeline.equals("Yes")) {
                        lifelines(questions, options, answers, i, options50_50, exchangequestion, exchangeoption, exchange50_50option, exchangequeanswer);
                        break;
                    } else if (takeLifeline.equals("No") || takeLifeline.equals("NO") || takeLifeline.equals("no")) {
                        System.out.println();
                        System.out.print("Enter Your Answer: ");
                        String answer = sc.nextLine();
                        while (true) {
                            if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4")) {
                                if (answer.equals(answers[i])) {
                                    printRightWrongAnswer(true);
                                    score++;
                                } else {
                                    printRightWrongAnswer(false);
                                }
                                break;
                            } else {
                                System.out.print("Enter Valid Choice(Between 1 To 4): ");
                                answer = sc.nextLine();
                            }
                        }
                        break;
                    } else {
                        System.out.print("Enter Yes Or No: ");
                        takeLifeline = sc.nextLine();
                    }
                }
            }
            break;
        }
    }

    void lifelinetable(boolean lifeline50_50,boolean lifelineExchangeQuestion,boolean lifelineExpertAdvise){
            System.out.println();
            System.out.println("YOUR LIFELINES");
            System.out.println("----------------------------------");
            if (!lifeline50_50) {
                System.out.println("| 1. 50-50 Lifeline              |");
            }
            if (!lifelineExchangeQuestion) {
                System.out.println("| 2. Exchange Questions          |");
            }
            if (!lifelineExpertAdvise) {
                System.out.println("| 3. Expert Advise                |");
            }
            System.out.println("----------------------------------");
        System.out.println();
        if (lifelinesUsed == 0) {
            System.out.println("Press 1 For 50-50 Lifeline");
            System.out.println("Press 2 For Exchange Question Lifeline");
            System.out.println("Press 3 For Expert Advise Lifeline");
        }
    }

    void lifelines(String []questions,String []options,String []answers,int i,String []options50_50,String exchangequestion,String exchangeoption,String exchange50_50option,String exchangequeanswer) {

                lifelinetable(lifeline50_50, lifelineExchangeQuestion, lifelineExpertAdvise);
                System.out.println();
                boolean flag = true;
                System.out.print("Enter Your Choice: ");
                String choice = sc.nextLine();

                while (true) {
                    if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
//                        sc.nextLine();
                        System.out.println();

                        switch (choice) {
                            case "1":
                                if (!lifeline50_50) {
                                    lifeline50_50 = true;
                                    lifeline50_50(questions, options, answers, i, options50_50, lifeline50_50, lifelineExchangeQuestion, lifelineExpertAdvise, exchangequestion, exchangeoption, exchange50_50option, exchangequeanswer);
                                } else {
                                    System.out.println("You Have Already Use 50-50 Lifeline.");
                                    System.out.println();
                                    lifelines(questions, options, answers, i, options50_50, exchangequestion, exchangeoption, exchange50_50option, exchangequeanswer);
                                }
                                break;

                            case "2":
                                if (!lifelineExchangeQuestion) {
                                    lifelineExchangeQuestion = true;
                                    lifelineExchangeQuestion(questions, options, answers, i, options50_50, lifeline50_50, lifelineExchangeQuestion, lifelineExpertAdvise, exchangequestion, exchangeoption, exchange50_50option, exchangequeanswer);
                                } else {
                                    System.out.println("You Have Already Use ExchangeQuestion Lifeline.");
                                    System.out.println();
                                    lifelines(questions, options, answers, i, options50_50, exchangequestion, exchangeoption, exchange50_50option, exchangequeanswer);
                                }
                                break;

                            case "3":
                                if (!lifelineExpertAdvise) {
                                    lifelineExpertAdvise = true;
                                    lifelineExpertAdvise(questions, options, answers, i, options50_50, lifeline50_50, lifelineExchangeQuestion, lifelineExpertAdvise, exchangequestion, exchangeoption, exchange50_50option, exchangequeanswer);
                                } else {
                                    System.out.println("You Have Already Use ExpertAdvise Lifeline.");
                                    System.out.println();
                                    lifelines(questions, options, answers, i, options50_50, exchangequestion, exchangeoption, exchange50_50option, exchangequeanswer);
                                }
                                break;
                        }
                        break;
                    }
                    else {
                        System.out.println("Enter Valid Choice (Between 1 To 3)");
                        choice = sc.nextLine();
                    }
                }
        howmanyLifelinesUsed();
            }
        void howmanyLifelinesUsed(){
            System.out.println();
            if (lifelinesUsed > 0) {
                System.out.println("You have " + (3 - lifelinesUsed) + " lifeline(s) remaining.");
                System.out.println();
                if (lifeline50_50) {
                    System.out.println("50-50 lifeline used.");
                }
                if (lifelineExchangeQuestion) {
                    System.out.println("Exchange question lifeline used.");
                }
                if (lifelineExpertAdvise) {
                    System.out.println("Expert advice lifeline used.");
                }
                System.out.println();
            }
        }
    void lifeline50_50(String []questions,String []options,String []answers,int i,String []options50_50,boolean lifeline50_50,boolean lifelineExchangeQuestion,boolean lifelineExpertAdvise,String exchangequestion,String exchangeoption,String exchange50_50option,String exchangequeanswer){
        for(i=0;i<10;i++) {
            if (i == this.i) {
                System.out.println(questions[i]);
                System.out.println(options50_50[i]);
                System.out.println();
                lifelinesUsed++;
                while (true) {
                    if (lifelinesUsed == 3) {
                        System.out.println();
                        System.out.println("YOU HAVE NO LIFELINE");
                        System.out.println();
                        System.out.print("Enter Your Answer: ");
                        String answer = sc.nextLine();
                        while (true) {
                            if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4")) {
                                if (answer.equals(answers[i])) {
                                    printRightWrongAnswer(true);
                                    score++;
                                } else {
                                    printRightWrongAnswer(false);
                                }
                                break;
                            } else {
                                System.out.print("Enter Valid Choice(Between 1 To 4): ");
                                answer = sc.nextLine();
                            }
                        }
                        break;
                    }

                       else{
                            System.out.println("If You Still Want To Use Lifeline Enter Yes And If You Don't Want To Use Enter No!");
                            System.out.println();
                            System.out.print("What's Your Choice: ");
                            String takeLifeline = sc.nextLine();
                            while (true) {
                                if (takeLifeline.equals("yes") || takeLifeline.equals("YES") || takeLifeline.equals("Yes")) {
                                    lifelines(questions, options, answers, i, options50_50, exchangequestion, exchangeoption, exchange50_50option, exchangequeanswer);
                                    break;
                                } else if (takeLifeline.equals("No") || takeLifeline.equals("NO") || takeLifeline.equals("no")) {
                                    System.out.println();
                                    System.out.print("Enter Your Answer: ");
                                    String answer = sc.nextLine();
                                    while (true) {
                                        if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4")) {
                                            if (answer.equals(answers[i])) {
                                                printRightWrongAnswer(true);
                                                score++;
                                            } else {
                                                printRightWrongAnswer(false);
                                            }
                                            break;
                                        } else {
                                            System.out.print("Enter Valid Choice(Between 1 To 4): ");
                                            answer = sc.nextLine();
                                        }
                                    }
                                    break;
                                } else {
                                    System.out.print("Enter Yes Or No: ");
                                    takeLifeline = sc.nextLine();
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }

     void  lifelineExchangeQuestion(String []questions,String []options,String []answers,int i,String []options50_50,boolean lifeline50_50,boolean lifelineExchangeQuestion,boolean lifelineExpertAdvise,String exchangequestion,String exchangeoption,String exchange50_50options,String exchangequeanswer) {
         System.out.println("YOUR QUESTION IS EXCHANGED");
         System.out.println();
         System.out.println(exchangequestion);
         System.out.println(exchangeoption);
         System.out.println();
         lifelinesUsed++;
         while (true) {
             if (lifelinesUsed == 3) {
                 System.out.println();
                 System.out.println("YOU HAVE NO LIFELINE!!");
                 System.out.println();
                 System.out.print("Enter Your Answer: ");
                 String answer = sc.nextLine();
                 while (true) {
                     if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4")) {
                         if (answer.equals(exchangequeanswer)) {
                             printRightWrongAnswer(true);
                             score++;
                         } else {
                             printRightWrongAnswer(false);
                         }
                         break;
                     } else {
                         System.out.print("Enter Valid Choice(Between 1 To 4): ");
                         answer = sc.nextLine();
                     }
                 }
                 break;
             } else {
                 System.out.println("If You Still Want To Use Lifeline Enter Yes And If You Don't Want To Use Enter No!");
                 System.out.println();
                 System.out.print("What's Your Choice: ");
                 String takeLifeline = sc.nextLine();
                 System.out.println();
                 while (true) {
                     if (takeLifeline.equals("yes") || takeLifeline.equals("YES") || takeLifeline.equals("Yes")) {
                         lifelinetable(lifeline50_50, lifelineExchangeQuestion, lifelineExpertAdvise);
                         System.out.println();
                         System.out.print("Enter Your choice: ");
                         int choice = sc.nextInt();
                         if (choice == 1) {
                             if (!lifeline50_50) {
                                 lifeline50_50WithExchange(exchangequestion, exchange50_50options, exchangequeanswer);
                                 break;
                             }
                         } else if (choice == 3) {
                             if (!lifelineExpertAdvise) {
                                 lifelineExpertWithExchange(exchangequestion, exchangeoption, exchangequeanswer);
                                 break;
                             }
                         }
                     } else if (takeLifeline.equals("No") || takeLifeline.equals("NO") || takeLifeline.equals("no")) {
                         System.out.println();
                         System.out.print("Enter Your Answer: ");
                         String answer = sc.nextLine();
                         while (true) {
                             if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4")) {
                                 if (answer.equals(exchangequeanswer)) {
                                     printRightWrongAnswer(true);
                                     score++;
                                 } else {
                                     printRightWrongAnswer(false);
                                 }
                                 break;
                             } else {
                                 System.out.print("Enter Valid Choice(Between 1 To 4): ");
                                 answer = sc.nextLine();
                             }
                         }
                         break;
                     } else {
                         System.out.print("Enter Yes Or No: ");
                         takeLifeline = sc.nextLine();
                     }
                 }
                 break;
             }
         }
     }

     void lifeline50_50WithExchange(String exchanegequestion,String exchange50_50option,String exchangequeanswer){
         lifeline50_50=true;
         lifelinesUsed++;
         System.out.println(exchanegequestion);
         System.out.println(exchange50_50option);
         System.out.print("Enter Your Answer: ");
         sc.nextLine();
         String answer=sc.nextLine();
         if(answer.equals(exchangequeanswer)){
             printRightWrongAnswer(true);
             score++;
         }
         else{
             printRightWrongAnswer(false);
         }
     }

     void lifelineExpertWithExchange(String exchanegequestion,String exchangeoption,String exchangequeanswer){
         lifelineExpertAdvise=true;
         lifelinesUsed++;
         System.out.println(exchanegequestion);
         System.out.println(exchangeoption);
         System.out.println();
         System.out.println("Expert Advise You To Choose: "+exchangequeanswer+" option");
         System.out.println();
         System.out.print("Enter Your Answer: ");
         sc.nextLine();
         String answer=sc.nextLine();
         if(answer.equals(exchangequeanswer)){
             printRightWrongAnswer(true);
             score++;
         }
         else{
             printRightWrongAnswer(false);
         }
     }

     void lifelineExpertAdvise(String []questions,String []options,String []answers,int i,String []options50_50,boolean lifeline50_50,boolean lifelineExchangeQuestion,boolean lifelineExpertAdvise,String exchangequestion,String exchangeoption,String exchange50_50option,String exchangequeanswer){
         System.out.println(questions[i]);
         System.out.println(options[i]);
         lifelinesUsed++;
         System.out.println();
         System.out.print("Expert Advise You To Choose: ");
         System.out.println(answers[i]+" option");
         System.out.println();
         System.out.print("Enter Your Answer: ");
         String answer=sc.nextLine();
         while(true) {
             if (answer.equals("1") || answer.equals("2") || answer.equals("3") || answer.equals("4")) {
                 if (answer.equals(answers[i])) {
                     printRightWrongAnswer(true);
                     score++;
                 } else {
                     printRightWrongAnswer(false);
                 }
                 break;
             }
             else {
                 System.out.print("Enter Valid Answer(Between 1 to 4): ");
                 answer=sc.nextLine();
             }
         }
    }
     int score(){
        return score;
     }
    static void printRightWrongAnswer(boolean flag){
        if(flag){
            System.out.println("--------------------");
            System.out.println("|  Right Answer!!  |");
            System.out.println("--------------------");
            System.out.println();
        }
        else{
            System.out.println("--------------------");
            System.out.println("|  Wrong Answer!!  |");
            System.out.println("--------------------");
            System.out.println();
        }
    }

}

class General_Knowledge_Quiz extends QuizMain
{
    static int gkscore=0;
    int i=0;
    String answers[]={"1","3","2","1","1","3","4","1","3","4"};
    String[] questions = {"Which country has the largest land area in the world?",
            "Which planet is known as the Red Planet?",
            "Which famous British physicist wrote the book A Brief History of Time?",
            "What is the name of the highest mountain peak in India?",
            "Who is the current Prime Minister of Canada?",
            "What is the currency of Brazil?",
            "Which Indian state is known as the \"Spice Garden of India\"?",
            "Which Indian state is home to the Kaziranga National Park, known for its one-horned rhinoceros?",
            "Which Indian state is known as the \"Land of Festivals\"?",
            "What is the capital of Australia?"};
    String[] options = {"(A) Russia\n(B) China\n(C) Canada\n(D) USA",
            "(A) Jupiter\n(B) Venus\n(C) Mars\n(D) Saturn",
            "(A) Albert Einstein\n(B) Stephen Hawking\n(C) Neil deGrasse Tyson\n(D) Richard Feynman",
            "(A) Kanchenjunga\n(B) Mount Everest\n(C) Nanda Devi\n(D) Annapurna",
            "(A) Justin Trudeau\n(B) Stephen Harper\n(C) Jean Chretien\n(D) Brian Mulroney",
            "(A) Euro\n(B) Yen\n(C) Real\n(D) Rupee",
            "(A) Andhra Pradesh\n(b) Karnataka\n(C) Tamil Nadu\n(D) Kerala",
            "(A) Assam\n(B) Meghalaya\n(C) Tripura\n(D) Mizoram",
            "(A) Rajasthan\n(B) Gujarat\n(C) West Bengal\n(D) Tamil Nadu",
            "(A) Sydney\n(B) Melbourne\n(C) Perth\n(D) Canberra"};
    String[] options50_50 = {"(A) Russia\n(B) China\n\n",
            "(A) Jupiter\n\n(C) Mars\n",
            "(A) Albert Einstein\n(B) Stephen Hawking\n\n",
            "(A) Kanchenjunga\n(B) Mount Everest\n\n",
            "(A) Justin Trudeau\n\n(C) Jean Chretien\n",
            "(A) Euro\n\n(C) Real\n",
            "\n\n(C) Tamil Nadu\n(D) Kerala",
            "(A) Assam\n(B) Meghalaya\n\n",
            "\n(B) Gujarat\n(C) West Bengal\n\n",
            "(A) Sydney\n\n\n(D) Canberra"};
    String exchangequestion="Which country is both the smallest by land area and the smallest by population?";
    String exchangeoption="(A)Marino\n(B)Nauru\n(C)Monaco\n(D)Vatican City";
    String exchange50_50option="(A)Marino\n\n\n(D)Vatican City";
    String exchangequeanswer="4";
    int lifelinesUsed=0;
   void genrealKnowlegde(){
       setLifelinesUsed(lifelinesUsed,score);
       printQuestions(questions,options,answers,options50_50,exchangequestion,exchangeoption,exchange50_50option,exchangequeanswer,i);
       System.out.println();
       System.out.println("YOU COMPLETED GENERAL-KNOWLEDGE QUIZ!!");
       System.out.println();
       gkscore=score();
       System.out.println("YOUR SCORE IN GENERAL KNOWLEDGE QUIZ IS: "+gkscore+"/10");
       System.out.println();
   }
}

class History_Quiz extends QuizMain
{
    static int historyscore=0;
    int i=0;
    String answers[]={"2", "3", "3", "3", "1", "4", "4", "1", "2", "2"};
    String[] questions = {"In Which Country Pyramids Of Giza Were Bulid?",
            "In which year did World War 1 begin?",
            "Where did the great battle between alexender the great and king poras takes place?",
            "In Which Year Bhopal Gas Tragedy Took Place?",
            "Which Is The Longest Ruling Dynasty In The History Of India?",
            "During The Mughal Era Which Part Of The Great India Was Not Captured By Them?",
            "During The American Civil War Who Was President Of The America?",
            "Who was the first woman to win a Nobel Prize?",
            "Who Was The Last Emperor Of Rom?",
            "In Which Country The First Revolution 1848 Begin That Lead To A Series Of Revolution?"};
    String[] options = {"(A) Jordan\n(B) Egypt\n(C) Libya\n(D) Uzbekistan",
            "(A) 1916\n(B) 1920\n(C) 1914\n(D) 1918",
            "(A) HaldiGhati\n(B) In The Outskirts Of Afghanistan\n(C) Banks Of Hydaspes River\n(d) Panipat",
            "(A) 1974\n(B)1986\n(C) 1984\n(D)1983",
            "(A) Mourya Dynasty\n(B) Mughal Dynasty\n(C) Rashtrakuta\n(D) chola Dynasty",
            "(A) Southern Indian\n(B) Western India\n(C) Middle-East India\n(D) Northern Parts Of Himalaya",
            "(A) George Washington\n(B) Andrew Jackson\n(C) Andrew Johnson\n(D) Abhraham Lincoln",
            "(A) Marie Curie\n(B) Mother Teresa\n(C) Florence Nightingale\n(D) Rosa Parks",
            "(A) Maxentius\n(B) Flavius Romulus\n(C) Nero Cladius\n(D)Caesar Vespasian",
            "(A) India During British Rule\n(B) Italy During Rebalioun Wars\n(C) China Revolution\n(D) None Of The Above"};
    String[] options50_50={"\n(B)Egypt\n(C)Libya\n",
            "(A)1916\n\n(C)1914\n",
            "\n\n(C)Banks Of Hydaspes River\n(D)Panipat",
            "(A)1974\n\n(C)1984\n",
            "(A)Southern Indian\n\n\n(D)Notheren Parts Of Himalaya",
            "\n\n(C)Rashtrakuta\n(D)chola Dynasty",
            "(A) George Washington\n\n\n(D)Abhraham Lincoln",
            "(A) Marie Curie\n(B) Mother Teresa\n\n",
            "\n(B)Flavius Romulus\n(C)Nero Cladius\n",
            "\n(B)Italy During Rebalioun Wars\n\n(D)None Of The Above"};
    String exchangequestion="Who was the first female ruler of India, who reigned from 1230 to 1260 CE, and is known for her military conquests and patronage of the arts?";
    String exchangeoption="(A) Razia Sultan\n(B) Chand Bibi\n(C) Durgavati\n(D) Nur Jahan";
    String exchange50_50option="(A) Razia Sultan\n\n\n(D) Nur Jahan";
    String exchangequeanswer="1";
    int lifelineUsed=0;

    void historyRelated(){
        setLifelinesUsed(lifelinesUsed,score);
        printQuestions(questions,options,answers,options50_50,exchangequestion,exchangeoption,exchange50_50option,exchangequeanswer,i);
        System.out.println();
        System.out.println("YOU HAVE COMPLETED HISTORY QUIZ!!");
        System.out.println();
        historyscore=score();
        System.out.println("YOUR SCORE IN HISTORY QUIZ IS: "+historyscore+"/10");
        System.out.println();
    }
}

class Sports_Quiz extends QuizMain
{
    static int sportsscore=0;
    int i=0;
    String answers[]={"1","2","2","4","3","1","4","2","1","3"};
    String [] questions={"Which country has won the most FIFA World Cup titles?",
            "Where are the headquarters of International World Games Association?",
            "Which Batsman Scored All The Centuries With 100+ StrikeRate In ODI Cricket?",
            "Who has joint most Formula One championship title?",
            "Which Player Included In Team Declared By The ICC Between The Decade 2010 To 2020?",
            "What Team Owns Longest Winning Streaks In NBA?",
            "In Which Years India Won The ODI World-cups?",
            "Which country’s national football team is nicknamed as \"Les Bleus (The Blues)\"?",
            "What Type Of Race Of Tour De France?",
            "Who Is The Only Indian Athlete To Win Consicutive Medal In Two Consicutive Olympics?"};
    String [] options ={"(A) Brazil\n(B) Germany\n(C) Argentina\n(D) Italy",
            "(A)Germany\n(B)Switzerland\n(C)England\n(D)France",
            "(A)Sanath Jaisurya\n(B)A b DeVilliers\n(C)Yuvraj Singh\n(D)Chris Gayle",
            "(A) Lewis Hamilton\n(B) Michael Schumacher\n(C) Sebastian Vettel\n(D)Both (A) & (C)",
            "(A)A b DeVilliers\n(B)Mitchel Stark\n(C)Virat Kohli\n(D)Dale Steyn",
            "(A)Los Angeles Lakers\n(B)Chicago Bulls\n(C)Los Angeles Clippers\n(D)Dalas Mevricks",
            "(A)1979,2011\n(B)1983,2003\n(C)1991,2015\n(D)1983,2011",
            "(A)Portugal\n(B)France\n(C)Spain\n(D)Italy",
            "(A)Bycycle Race\n(B)Running Race\n(C)Car Race\n(D)None Of The Above",
            "(A)Mary Kom\n(B)Sakshi Malik\n(C)P V Sindhu\n(D)Mirabai Chanu"};

    String[] options50_50={"(A)Brazil\n(B)Germany\n\n",
            "\n(B)Switzerland\n(C)England\n",
            "\n(B)A B De Villiers\n(C)Yuvraj Singh\n",
            "\n\n(C) Sebastian Vettel\n(D)Both (A) & (C)",
            "(A)A B De Villiers\n\n(C)Virat Kohli\n",
            "(A)Los Angeles Lakers\n\n(C)Los Angeles Clippers\n",
            "(A)1979,2011\n\n\n(D)1983,2011",
            "\n(B)France\n\n(D)Italy",
            "(A)Bicycle Race\n(B)Running Race\n\n",
            "(A)Mary Kom\n\n(C)P V Sindhu\n"};

    String exchangequestion="Which boxer famously proclaimed \"I am the greatest\" and had a record of 56 wins, 5 losses, and 37 knockouts in his career?";
    String exchangeoption="(A) Muhammad Ali\n(B) Mike Tyson\n(C) Floyd Mayweather Jr.\n(D) Sugar Ray Leonard";
    String exchange50_50option="(A) Muhammad Ali\n(B) Mike Tyson\n\n";
    String exchangequeanswer="1";
    int lifelineUsed=0;

    void sportsRelated(){
        setLifelinesUsed(lifelinesUsed,score);
        printQuestions(questions,options,answers,options50_50,exchangequestion,exchangeoption,exchange50_50option,exchangequeanswer,i);
        System.out.println();
        System.out.println("YOU COMPLETED SPORTS QUIZ!!");
        System.out.println();
        sportsscore=score();
        System.out.println("YOUR SCORE IN SPORTS QUIZ IS: "+sportsscore+"/10");
        System.out.println();
    }
}

class Java_Quiz extends QuizMain
{
    static int javascore=0;
    int i=0;
    String answers[]={"1","3","2","4","2","2","3","1","3","4"};
    String[] questions = {"Who Invented Java Programming?",
            "Identify the corrected definition of a package?",
            "When is the object created with new Keyword?",
            "Which of these are selection statements in java?",
            "Which of the following is a type of polymorphism in java Programming?",
            "What is not the use of (this) Keyword in java?",
            "What is the extension of compiled java classes?",
            "In Which of the following is toString() method defined?",
            "A variable is a",
            "Variable declaration defines"};

    String[] options = {"(A)James Gosling\n(B)Guido Van Rossum\n(C)Dennis Ritchie\n(D)Bjarne Stroustrup",
            "(A)A package is a collection of editing tools\n(B)A package is a collection of classes\n(C)A package is a collection of classes and interfaces\n(D)A package is a collection of interfaces",
            "(A)At compile time\n(B)At run time\n(C)Depends on the code\n(D)None",
            "(A)break\n(B)continue\n(C)\nfor()\n(D)if()",
            "(A)Multiple polymorphism\n(B)Compile polymorphism\n(C)Multilevel polymorphism\n(D)Execution time polymorphism",
            "(A)Referring to the instance variable when a local variable has the same name\n(B)Passing itself to the method of the same class\n(C)Passing itself to another method\n(D)Calling another constructor in constructor chaining",
            "(A) .txt\n(B) .js\n(C)\n .class\n(D) .java",
            "(A)java.lang.Object\n(B)java.lang.String\n(C)java.lang.util\n(D)None",
            "(A)name that represents a value\n(B)reference to the memory location that holds a value\n(C)both (A) & (B)\n(D)none of the above",
            "(A)only the name of the variable\n(B)an address in the variable\n(C)the type of data the variable can hold\n(D)both (A) & (C)"};

    String[] options50_50= {"(A)James Gosling\n(B)Guido Van Rossum\n\n",
            "\n(B)A package is a collection of classes\n(C)A package is a collection of classes and interfaces\n",
            "(A)At compile time\n(B)At run time\n\n",
            "\n\n(C)\nfor()\n(D)if()",
            "(A)Multiple polymorphism\n\n(C)Multilevel polymorphism\n",
            "(A)Referring to the instance variable when a local variable has the same name\n(B)Passing itself to the method of the same class\n\n",
            "\n\n(C)\n .class\n(D) .java",
            "(A)java.lang.Object\n(B)java.lang.String\n\n",
            "\n\n(C)both (A) & (B)\n(D)none of the above",
            "\n(B)an address in the variable\n\n(D)both (A) & (C)"};

    String exchangequestion="What is the output of the following Java code?\n\n" +
            "for (int i = 0; i < 5; i++) {\n" +
            "if (i == 3) {\n" +
            "   continue;\n" +
            "}\n" +
            "System.out.print(i + \" \");\n" +
            "}";
    String exchangeoption="(A)  0 1 2 4\n(B) 0 1 2 3 4\n(C) 0 1 2\n(D) 3 4 5";
    String exchange50_50option="(A)  0 1 2 4\n\n(C) 0 1 2\n";
    String exchangequeanswer="1";
    int lifelineUsed=0;

    void javaRelated(){
        setLifelinesUsed(lifelinesUsed,score);
        printQuestions(questions,options,answers,options50_50,exchangequestion,exchangeoption,exchange50_50option,exchangequeanswer,i);
        System.out.println();
        System.out.println("YOU COMPLETED JAVA QUIZ!!");
        System.out.println();
        javascore=score();
        System.out.println("YOUR SCORE IN JAVA QUIZ IS: "+javascore+"/10");
        System.out.println();
    }
}

class Logical_Quiz extends QuizMain
{
    static int logicalscore=0;
    int i=0;
    String answers[]={"1","3","2","4","2","4","2","2","3","4"};
    String[] questions ={"A boy and a doctor were fishing. The boy is the doctor's son,but the doctor isn't the boy's father. Who is the doctor?",
            "X introduces Y saying, \"He is the husband of the grand daughter of the father of my father\". How is Y related to X?",
            "If \"PIPE\" is written as 169165, then what is the last digit os \"SWAN\"?",
            "If \"NOIDA\" is written as \"OPJEB\", then what will be the code for \"DELHI\"?",
            "Which number should come next in the series 1,2,3,10,__",
            "Which of the following is the odd number from the given alternatives?",
            "Raman says \"Anuj's mother is the only daughter of my mother.\" How is Anuj related to Raman?",
            "What will be the missing letters in the series BKK,DMM,FOO,___,JSS?",
            "PASTORAL : RURAL",
            "At the baseball game, Henry was sitting in seat 253. Marla was sitting to the right of Henry in seat 254. In the seat to the left of Henry was George. Inez was sitting to the left of George. Which seat is Inez sitting in?"};
    String[] options ={"(A)Mother\n(B)Brother\n(C)Sister\n(D)Grandmother",
            "(A)Brother\n(B)Uncle\n(C)Brother-in-law\n(D)Co-brother",
            "(A)5\n(B)4\n(C)6\n(D)3",
            "(A)EFMAK\n(B)EFAMK\n(C)EFMIK\n(D)EFMIJ",
            "(A)79\n(B)99\n(C)89\n(D)98",
            "(A)1090\n(B)962\n(C)626\n(D)841",
            "(A)Brother\n(B)Nephew\n(C)Father\n(D)None of the above",
            "(A)HTT\n(B)HQQ\n(C)HPP\n(D)None of the above",
            "(A)sleepy : nocturnal\n(B)harvest : autumn\n(C)metropolitan : urban\n(D)agrarian : benevolent",
            "(A)254\n(B)255\n(C)256\n(D)251"};
    String[] options50_50={"(A)Mother\n\n(C)Sister\n",
            "(A)Brother\n\n(C)Brother-in-law\n",
            "\n(B)4\n\n(D)3",
            "\n\n(C)EFMIK\n(D)EFMIJ",
            "\n(B) 99\n\n(D)\n98",
            "(A)1090\n\n\n(D)841",
            "(A)Brother\n(B)Nephew\n\n",
            "\n(B)HQQ\n(C)HPP\n",
            "(A)sleepy : nocturnal\n\n(C)metropolitan : urban\n",
            "(A)254\n\n\n(D)251"};

    String exchangequestion="If you rearrange the letters \"LNGEDNA\" you get the name of a(n):";
    String exchangeoption="(A) Animal\n(B) Country\n(C) Ocean\n(D) Planet";
    String exchange50_50option="\n(B) Country\n\n(D) Planet";
    String exchangequeanswer="4";
    int lifelineUsed=0;

    void logicalRelated(){
        setLifelinesUsed(lifelinesUsed,score);
        printQuestions(questions,options,answers,options50_50,exchangequestion,exchangeoption,exchange50_50option,exchangequeanswer,i);
        System.out.println();
        System.out.println("YOU COMPLETED LOGICAL QUIZ!!");
        System.out.println();
        logicalscore=score();
        System.out.println("YOUR SCORE IN LOGICAL QUIZ IS: "+logicalscore+"/10");
        System.out.println();
    }
}

public class QuizGame {
    static {
        System.out.println("                                    --------------------------------------------------------");
        System.out.println("                                    |                     LOGIN-PAGE                       |");
        System.out.println("                                    --------------------------------------------------------");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        QuizLoginMethods quiz = new QuizLoginMethods();
        quiz.login();
        System.out.println();
        quiz.validCaptcha();
        System.out.println("HERE SOME IMPORTANT INSTRUCTIONS AND RULES FOR QUIZ-GAME");
        quiz.quizGameInstructionsAndRules();
        System.out.println();
        quiz.validPassword();
        System.out.println("                                ---------------------------------------------------------------------------------------------------------------");
        System.out.println("                                |                                          WELCOME TO QUIZ-GAME                                               |");
        System.out.println("                                ---------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.println("THIS IS HOW YOU ENTER DIFFERENT TYPES OF QUIZ");
        int choice;
        do{
            System.out.println();
            System.out.println();
            if(!QuizMain.playGeneral) {
                System.out.println("PRESS 1 FOR GENERAL-KNOWLEDGE(GK) QUIZ");
            }
            if (!QuizMain.playHistory) {
                System.out.println("PRESS 2 FOR HISTORY QUIZ");
            }
            if (!QuizMain.playSports) {
                System.out.println("PRESS 3 FOR SPORTS QUIZ");
            }
            if(!QuizMain.playJava) {
                System.out.println("PRESS 4 FOR JAVA QUIZ");
            }
            if(!QuizMain.playLogical) {
                System.out.println("PRESS 5 FOR LOGICAL QUIZ");
            }
            System.out.println("PRESS 6 FOR QUIT THE QUIZ GAME");
            System.out.println();
            System.out.print("Enter Your Choice: ");
            choice =sc.nextInt();
            System.out.println();
            switch(choice){
                case 1:
                    General_Knowledge_Quiz  q1=new General_Knowledge_Quiz();;
                    if(!QuizMain.playGeneral) {
                        System.out.println("----------------------------------------");
                        System.out.println("|        GENERAL-KNOWLEDGE QUIZ        |");
                        System.out.println("----------------------------------------");
                        q1.genrealKnowlegde();
                        System.out.println("IF YOU WANT TO PLAY OTHER QUIZ GAME OR QUIT THE QUIZ GAME PLEASE FOLLOW BELOW STEPS");
                        QuizMain.playGeneral=true;
                    }
                    else {
                        System.out.println("YOU HAE ALREADY ATTEMPTED GENERAL-KNOWLEDGE QUIZ!!");
                    }
                    break;

                case 2:
                    History_Quiz q2=new History_Quiz();
                    if(!QuizMain.playHistory){
                        System.out.println("----------------------------------------");
                        System.out.println("|             HISTORY QUIZ             |");
                        System.out.println("----------------------------------------");
                        q2.historyRelated();
                        System.out.println("IF YOU WANT TO PLAY OTHER QUIZ GAME OR QUIT THE QUIZ GAME PLEASE FOLLOW BELOW STEPS");
                        QuizMain.playHistory=true;
                    }
                    else{
                        System.out.println("YOU HAVE ALREADY ATTEMPTED HISTORY QUIZ!!");
                    }
                    break;

                case 3:
                    Sports_Quiz q3 = new Sports_Quiz();
                    if(!QuizMain.playSports){
                        System.out.println("----------------------------------------");
                        System.out.println("|             SPORTS QUIZ              |");
                        System.out.println("----------------------------------------");
                        q3.sportsRelated();
                        System.out.println("IF YOU WANT TO PLAY OTHER QUIZ GAME OR QUIT THE QUIZ GAME PLEASE FOLLOW BELOW STEPS");
                        QuizMain.playSports=true;
                    }
                    else{
                        System.out.println("YOU HAVE ALREADY ATTEMPTED SPORTS QUIZ!!");
                    }
                    break;

                case 4:
                    Java_Quiz q4=new Java_Quiz();
                    if(!QuizMain.playJava){
                        System.out.println("----------------------------------------");
                        System.out.println("|               JAVA QUIZ              |");
                        System.out.println("----------------------------------------");
                        q4.javaRelated();
                        System.out.println("IF YOU WANT TO PLAY OTHER QUIZ GAME OR QUIT THE QUIZ GAME PLEASE FOLLOW BELOW STEPS");
                        QuizMain.playJava=true;
                    }
                    else {
                        System.out.println("YOU HAVE ALREADY ATTEMPTED JAVA QUIZ!!");
                    }
                    break;

                case 5:
                    Logical_Quiz q5=new Logical_Quiz();
                    if(!QuizMain.playLogical){
                        System.out.println("----------------------------------------");
                        System.out.println("|            LOGICAL QUIZ              |");
                        System.out.println("----------------------------------------");
                        q5.logicalRelated();
                        System.out.println("IF YOU WANT TO PLAY OTHER QUIZ GAME OR QUIT THE QUIZ GAME PLEASE FOLLOW BELOW STEPS");
                        QuizMain.playLogical=true;
                    }
                    else {
                        System.out.println("YOU HAVE ALREADY ATTEMPTED LOGICAL QUIZ!!");
                    }
                    break;

                case 6:
                    int count=0;
                    if(QuizMain.playGeneral==true){
                        count++;
                    }
                    if(QuizMain.playHistory==true){
                        count++;
                    }
                    if(QuizMain.playSports==true){
                        count++;
                    }
                    if(QuizMain.playJava==true){
                        count++;
                    }
                    if(QuizMain.playLogical==true){
                        count++;
                    }
                    System.out.println("YOU ATTEMPT TOTAL "+ count+ " Quiz.");
                    System.out.println();
                    System.out.print("THEY ARE:  ");
                    if(QuizMain.playGeneral==true){
                        System.out.print("GENERAL-KNOWLEDGE QUIZ,");
                    }
                    if(QuizMain.playHistory==true){
                        System.out.print(" HISTORY QUIZ,");
                    }
                    if(QuizMain.playSports==true){
                        System.out.print(" SPORTS QUIZ,");
                    }
                    if(QuizMain.playJava==true){
                        System.out.print(" JAVA QUIZ,");
                    }
                    if(QuizMain.playLogical==true){
                        System.out.print(" LOGICAL QUIZ");
                    }
                    int totalscore=General_Knowledge_Quiz.gkscore+History_Quiz.historyscore+Sports_Quiz.sportsscore+Java_Quiz.javascore+Logical_Quiz.logicalscore;
                    System.out.println();
                    System.out.println("-------------------------------------");
                    System.out.println("| YOUR TOTAL SCORE IS: "+totalscore+"         |");
                    System.out.println("-------------------------------------");
                    System.out.println();
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("|                                         THANK YOU FOR PARTICIPATING                                                             |");
                    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------");
                    break;
            }
        }while(choice!=6);
    }
}