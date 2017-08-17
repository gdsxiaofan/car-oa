package com.xiaofan.car.util.http;

import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.util.EncodingUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class CustomFilePart extends FilePart {
    public CustomFilePart(String filename, File file)
            throws FileNotFoundException {
        super(filename, file);
    }

    protected void sendDispositionHeader(OutputStream out) throws IOException {
        super.sendDispositionHeader(out);
        String filename = getSource().getFileName();
        if (filename != null) {
            out.write(EncodingUtil.getAsciiBytes(FILE_NAME));
            out.write(QUOTE_BYTES);
            out.write(EncodingUtil.getBytes(filename, "utf-8"));
            out.write(QUOTE_BYTES);
        }
    }
} 