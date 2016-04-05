package edu.petrov.gojavaonline.module999;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.msgpack.core.MessageBufferPacker;
import org.msgpack.core.MessagePack;
import org.msgpack.core.MessageUnpacker;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by anton on 30/03/16.
 */
public final class Serialization {

    private Serialization() {
    }

    private static String join(String[] in) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < in.length; ++i) {
            if (i > 0) {
                s.append(", ");
            }
            s.append(in[i]);
        }
        return s.toString();
    }

    public static void jacksonUsage() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Convert object to JSON string and save into a file directly
            Staff staff = new Staff("anton", 31, "programmer");
            mapper.writeValue(new File("staff.json"), staff);

            // Convert object to JSON string
            String jsonInString = mapper.writeValueAsString(staff);
            System.out.println(jsonInString);

            // Convert object to JSON string and pretty print
            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
            System.out.println(jsonInString);

            final Staff staff1 = mapper.readValue(jsonInString, Staff.class);
            System.out.println(staff1);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void messagePackBasicUsage() throws IOException {
        MessageBufferPacker packer = MessagePack.newDefaultBufferPacker();

        // pack binary data
        //byte[] ba = new byte[] {1, 2, 3, 4};
        //packer.packBinaryHeader(ba.length);
        //packer.writePayload(ba);

        packer
                .packInt(1)
                .packString("leo")
                .packArrayHeader(2)
                .packString("xxx-xxxx")
                .packString("yyy-yyyy");
        packer.close();

        MessageUnpacker unpacker = MessagePack.newDefaultUnpacker(packer.toByteArray());

        //unpacker.unpackBinaryHeader();
        //unpacker.readPayload(ba);

        int id = unpacker.unpackInt();
        String name = unpacker.unpackString();
        int numPhones = unpacker.unpackArrayHeader();
        String[] phones = new String[numPhones];
        for (int i = 0; i < numPhones; ++i) {
            phones[i] = unpacker.unpackString();
        }
        unpacker.close();

        System.out.println(String.format("id:%d, name:%s, phone:[%s]", id, name, join(phones)));
    }

    public static void classicBinarySerialization() throws Exception {
        RandomClass rc1 = new RandomClass();
        RandomClass rc2 = new RandomClass();
        Date now = new Date(System.currentTimeMillis());

        System.out.println(rc1);
        System.out.println(rc2);
        System.out.println(now);

        // serialize
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream( byteOutputStream
                /* new FileOutputStream("objects.dat") */ );

        outputStream.writeObject(rc1);
        outputStream.writeObject(rc2);
        outputStream.writeObject(now);
        outputStream.close();

        // deserialize
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteOutputStream.toByteArray()));
        RandomClass _rc1 = (RandomClass) inputStream.readObject();
        RandomClass _rc2 = (RandomClass) inputStream.readObject();
        Date _now = (Date) inputStream.readObject();
        inputStream.close();

        System.out.println(_rc1);
        System.out.println(_rc2);
        System.out.println(_now);
    }

    public static class RandomClass implements Serializable {

        private int data[];
        private transient List<Integer> data2 = new ArrayList<>(20);

        public RandomClass() {
            data = new int[r()];
            for (int i = 0; i < data.length; i++)
                data[i] = r();
        }

        private static int r() {
            return (int) (Math.random() * 10);
        }

        public void printout() {
            System.out.println("This RandomClass has " + data.length + " random integers");
            for (int i = 0; i < data.length; i++) {
                System.out.print(data[i] + ":");
                System.out.println();
            }
        }

        @Override
        public String toString() {
            return "RandomClass{" +
                    "data=" + Arrays.toString(data) +
                    '}';
        }
    }
}
