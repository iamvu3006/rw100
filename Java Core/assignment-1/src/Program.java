import java.util.Calendar;
import java.util.Date;
public class Program {
    public static void main(String[] args) {
        Department[] departments = new Department[5];
        String[] departmentNames = {"Sale", "Marketing", "Human Resource", "Security", "Development"};
        for (int i = 0; i < departments.length; i++) {
            departments[i] = new Department();
            departments[i].id = i + 1;
            departments[i].name = departmentNames[i];
        }
        Position[] positions = new Position[5];
        Position.PositionName[] positionNames = {
                Position.PositionName.DEV,
                Position.PositionName.TEST,
                Position.PositionName.SCRUM_MASTER,
                Position.PositionName.PM,
                Position.PositionName.DEV
        };
        for (int i = 0; i < positions.length; i++) {
            positions[i] = new Position();
            positions[i].id = i + 1;
            positions[i].positionName = positionNames[i];
        }
        Account[] accounts = new Account[5];
        String[] emails = {
                "hongnt1@gmail.com",
                "thuylt2@gmail.com",
                "nampt3@gmail.com",
                "anhnt4@gmail.com",
                "minhlt5@gmail.com"
        };
        String[] userNames = {"hongnt", "thuylt", "nampt", "anhnt", "minhlt"};
        String[] fullNames = {
                "Nguyen Thi Hong",
                "Le Thi Thuy",
                "Pham Van Nam",
                "Nguyen Thi Anh",
                "Le Minh Long"
        };
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
            accounts[i].id = i + 1;
            accounts[i].email = emails[i];
            accounts[i].userName = userNames[i];
            accounts[i].fullName = fullNames[i];
            accounts[i].department = departments[i];
            accounts[i].position = positions[i];
            accounts[i].createDate = createDate(2026, 4, i + 1);
        }
        Group[] groups = new Group[5];
        String[] groupNames = {"Java Core", "SQL Master", "QA Team", "Frontend Team", "Backend Team"};
        for (int i = 0; i < groups.length; i++) {
            groups[i] = new Group();
            groups[i].id = i + 1;
            groups[i].name = groupNames[i];
            groups[i].creator = accounts[i];
            groups[i].createDate = createDate(2026, 4, 6 + i);
        }
        GroupAccount[] groupAccounts = new GroupAccount[5];
        for (int i = 0; i < groupAccounts.length; i++) {
            groupAccounts[i] = new GroupAccount();
            groupAccounts[i].group = groups[i];
            groupAccounts[i].account = accounts[(i + 1) % accounts.length];
            groupAccounts[i].joinDate = createDate(2026, 4, 11 + i);
        }
        TypeQuestion[] typeQuestions = new TypeQuestion[5];
        TypeQuestion.TypeName[] typeNames = {
                TypeQuestion.TypeName.ESSAY,
                TypeQuestion.TypeName.MULTIPLE_CHOICE,
                TypeQuestion.TypeName.ESSAY,
                TypeQuestion.TypeName.MULTIPLE_CHOICE,
                TypeQuestion.TypeName.ESSAY
        };
        for (int i = 0; i < typeQuestions.length; i++) {
            typeQuestions[i] = new TypeQuestion();
            typeQuestions[i].id = i + 1;
            typeQuestions[i].typeName = typeNames[i];
        }
        CategoryQuestion[] categories = new CategoryQuestion[5];
        String[] categoryNames = {"Java", ".NET", "SQL", "Postman", "Ruby"};
        for (int i = 0; i < categories.length; i++) {
            categories[i] = new CategoryQuestion();
            categories[i].id = i + 1;
            categories[i].name = categoryNames[i];
        }
        Question[] questions = new Question[5];
        String[] questionContents = {
                "What is Java?",
                "What is SQL used for?",
                "What is REST API?",
                "What is SOLID?",
                "What is the purpose of a Git branch?"
        };
        for (int i = 0; i < questions.length; i++) {
            questions[i] = new Question();
            questions[i].id = i + 1;
            questions[i].content = questionContents[i];
            questions[i].category = categories[i];
            questions[i].type = typeQuestions[i];
            questions[i].creator = accounts[i];
            questions[i].createDate = createDate(2026, 4, 16 + i);
        }
        Answer[] answers = new Answer[5];
        String[] answerContents = {
                "Java is an object-oriented programming language.",
                "SQL is used to query data.",
                "REST API is an architectural style for client-server communication.",
                "SOLID is a set of software design principles.",
                "Git branches help manage independent development flows."
        };
        for (int i = 0; i < answers.length; i++) {
            answers[i] = new Answer();
            answers[i].id = i + 1;
            answers[i].content = answerContents[i];
            answers[i].question = questions[i];
            answers[i].isCorrect = true;
        }
        Exam[] exams = new Exam[5];
        String[] examCodes = {"EX001", "EX002", "EX003", "EX004", "EX005"};
        String[] examTitles = {
                "Java Core Exam",
                "SQL Basic Exam",
                "API Practice Exam",
                "OOP Review Exam",
                "Git Review Exam"
        };
        int[] durations = {45, 60, 30, 90, 120};
        for (int i = 0; i < exams.length; i++) {
            exams[i] = new Exam();
            exams[i].id = i + 1;
            exams[i].code = examCodes[i];
            exams[i].title = examTitles[i];
            exams[i].category = categories[i];
            exams[i].duration = durations[i];
            exams[i].creator = accounts[i];
            exams[i].createDate = createDate(2026, 4, 21 + i);
        }
        ExamQuestion[] examQuestions = new ExamQuestion[5];
        for (int i = 0; i < examQuestions.length; i++) {
            examQuestions[i] = new ExamQuestion();
            examQuestions[i].exam = exams[i];
            examQuestions[i].question = questions[i];
        }
        printDepartments(departments);
        printPositions(positions);
        printAccounts(accounts);
        printGroups(groups);
        printGroupAccounts(groupAccounts);
        printTypeQuestions(typeQuestions);
        printCategories(categories);
        printQuestions(questions);
        printAnswers(answers);
        printExams(exams);
        printExamQuestions(examQuestions);
    }
    private static Date createDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
    private static void printDepartments(Department[] departments) {
        System.out.println("Departments:");
        for (Department department : departments) {
            System.out.println(department.name);
        }
    }
    private static void printPositions(Position[] positions) {
        System.out.println("Positions:");
        for (Position position : positions) {
            System.out.println(position.positionName);
        }
    }
    private static void printAccounts(Account[] accounts) {
        System.out.println("Accounts:");
        for (Account account : accounts) {
            System.out.println(account.fullName);
        }
    }
    private static void printGroups(Group[] groups) {
        System.out.println("Groups:");
        for (Group group : groups) {
            System.out.println(group.name);
        }
    }
    private static void printGroupAccounts(GroupAccount[] groupAccounts) {
        System.out.println("GroupAccounts:");
        for (GroupAccount groupAccount : groupAccounts) {
            System.out.println(groupAccount.group.name + " - " + groupAccount.account.fullName);
        }
    }
    private static void printTypeQuestions(TypeQuestion[] typeQuestions) {
        System.out.println("TypeQuestions:");
        for (TypeQuestion typeQuestion : typeQuestions) {
            System.out.println(typeQuestion.typeName);
        }
    }
    private static void printCategories(CategoryQuestion[] categories) {
        System.out.println("CategoryQuestions:");
        for (CategoryQuestion category : categories) {
            System.out.println(category.name);
        }
    }
    private static void printQuestions(Question[] questions) {
        System.out.println("Questions:");
        for (Question question : questions) {
            System.out.println(question.content);
        }
    }
    private static void printAnswers(Answer[] answers) {
        System.out.println("Answers:");
        for (Answer answer : answers) {
            System.out.println(answer.content);
        }
    }
    private static void printExams(Exam[] exams) {
        System.out.println("Exams:");
        for (Exam exam : exams) {
            System.out.println(exam.code);
        }
    }
    private static void printExamQuestions(ExamQuestion[] examQuestions) {
        System.out.println("ExamQuestions:");
        for (ExamQuestion examQuestion : examQuestions) {
            System.out.println(examQuestion.exam.code + " - " + examQuestion.question.content);
        }
    }
}
