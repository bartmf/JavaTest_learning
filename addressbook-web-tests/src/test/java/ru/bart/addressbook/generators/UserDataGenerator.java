package ru.bart.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.bart.addressbook.model.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class UserDataGenerator {
    @Parameter(names = "-c", description = "User count")
    public static int count;

    @Parameter(names = "-f", description = "Target file")
    public static String file;

    public static void main(String[] args) throws IOException {
        UserDataGenerator generator = new UserDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<UserData> users = generateUsers(count);
        saveAsJson(users, new File(file));
    }

    private static void saveAsJson(List<UserData> users, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(users);
        Writer writer = new FileWriter(file);
        writer.write(json);
        writer.close();
    }


    private static List<UserData> generateUsers(int count) {
        List<UserData> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(new UserData().withName(String.format("test %s", i))
                    .withMiddleName(String.format("Middle Name %s", i))
                    .withLastName(String.format("last Name %s", i))
                    .withNickName(String.format("Nick Name %s", i))
                    .withTitle(String.format("Title %s", i))
                    .withCompany(String.format("Company %s", i))
                    .withAddress(String.format("Address %s", i))
                    .withHomePhone(String.format("+7 %s", i))
                    .withMobilePhone(String.format("+9 %s", i))
                    .withEmail(String.format("Email %s", i))
                    .withEmail2(String.format("Email 2 %s", i))
                    .withEmail3(String.format("Email 3 %s", i)));
        }
        return users;
    }
}
