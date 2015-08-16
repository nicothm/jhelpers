package devtools.rsc;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;

/**A Resource-manager which takes a root-directory for all resources. (e.g: ./rsc; ./resources)
 * This is useful for loading files from this directory.
 *
 * Created by nico on 16.08.15.
 */
public class ResourceManager {
    private final String root;
    private final String fileSeparator = FileSystems.getDefault().getSeparator();

    /**
     * Creates a new manager with the given path as root-path.
     * @param rootpath the root-directory of the rsc-pathes.
     */
    public ResourceManager(String rootpath) {
        root = rootpath;
    }

    /**
     * Returns a new resource from the given subpath, concatenated with the rootpath.
     * E.g.: /home/nick/resources, where /home/nick is root and resources the subpath.
     * @param subpath the path witihn the rootpath of this manager
     * @return a new URL to the resource
     */
    public URL getRsc(String subpath) {
        return getClass().getResource(root+fileSeparator+subpath);
    }

    /**
     * Returns a new inputstream from the given subpath, concatenated with the rootpath.
     * E.g.: /home/nick/resources, where /home/nick is root and resources the subpath.
     * @param subpath the path witihn the rootpath of this manager
     * @return a new INputStream from the resource
     */
    public InputStream getRscAsStream(String subpath) {
        return getClass().getResourceAsStream(root+fileSeparator+subpath);
    }

    /**
     * Returns the root-directory.
     * @return the roo directory as string
     */
    public String getRoot() {
        return root;
    }
}
