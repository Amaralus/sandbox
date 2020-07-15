package amaralus.apps.sandbox;

import amaralus.apps.sandbox.entities.SomeClass;
import amaralus.apps.sandbox.entities.SomeClassEntry;
import org.msgpack.MessagePack;
import org.msgpack.packer.Packer;
import org.msgpack.template.Template;
import org.msgpack.unpacker.Unpacker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.msgpack.template.Templates.TByteArray;
import static org.msgpack.template.Templates.tList;

public class MsgPackSomeClassConverter {
    private MessagePack msgPack;

    public MsgPackSomeClassConverter() {
        msgPack = new MessagePack();
    }

    public byte[] serialize(SomeClass someClass) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (Packer packer = msgPack.createPacker(outputStream)) {
            packer.write(someClass.getSomeInt());
            packer.write(someClass.getSomeLong());
            packer.write(someClass.getSomeString());
            packer.write(serializeEntryList(someClass.getSomeClassEntries()));
        }

        return outputStream.toByteArray();

    }

    private List<byte[]> serializeEntryList(List<SomeClassEntry> entryList) throws Exception {
        List<byte[]> byteList = new ArrayList<>(entryList.size());

        for (SomeClassEntry someClassEntry : entryList)
            byteList.add(serializeEntry(someClassEntry));

        return byteList;
    }

    private byte[] serializeEntry(SomeClassEntry someClassEntry) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (Packer packer = msgPack.createPacker(outputStream)) {
            packer.write(someClassEntry.getSomeLong());
            packer.write(someClassEntry.getSomeString());
        }
        return outputStream.toByteArray();
    }

    public SomeClass deserialize(byte[] bytes) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        Template<List<byte[]>> listTemplate = tList(TByteArray);

        SomeClass someClass;
        try (Unpacker unpacker = msgPack.createUnpacker(inputStream)) {
            someClass = SomeClass.builder()
                    .someInt(unpacker.readInt())
                    .someLong(unpacker.readLong())
                    .someString(unpacker.readString())
                    .someClassEntries(deserializeEntryList(unpacker.read(listTemplate)))
                    .build();
        }

        return someClass;
    }

    private List<SomeClassEntry> deserializeEntryList(List<byte[]> byteList) throws Exception {
        List<SomeClassEntry> someClassEntryList = new ArrayList<>(byteList.size());

        for (byte[] bytes : byteList)
            someClassEntryList.add(deserializeEntry(bytes));

        return someClassEntryList;
    }

    private SomeClassEntry deserializeEntry(byte[] bytes) throws Exception {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

        SomeClassEntry someClassEntry;
        try (Unpacker unpacker = msgPack.createUnpacker(inputStream)) {
            someClassEntry = SomeClassEntry.builder()
                    .someLong(unpacker.readLong())
                    .someString(unpacker.readString())
                    .build();
        }

        return someClassEntry;
    }
}
