package org.sct.fv.loreattribute.util;

import org.sct.fv.loreattribute.file.*;

public class FileUtil {

    public static void loadFile() {
        Config.reload();
        Drop.reload();
        Message.reload();
        Rune.reload();
        Forge.reload();
    }
}
