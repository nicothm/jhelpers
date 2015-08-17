package devtools.rsc;

import devtools.debug.NullHelpers;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.Optional;

/**
 * A Resource-manager which takes a root-directory for all resources, like:
 * <UL>
 *     <LI>rsc</LI>
 *     <LI>resources</LI>
 *     <LI>assets</LI>
 * </UL>
 * <P>This is useful for loading files from this directory.</P>
 *
 * <P>Created by nico on 16.08.15.</P>
 */
public class ResourceManager {
    private final String root;
    private final String fileSeparator = FileSystems.getDefault().getSeparator();

    /**
     * Creates a new manager with the given path as root-path.
     *
     * @param rootpath the root-directory of the rsc-pathes.
     */
    public ResourceManager(String rootpath) {
        root = rootpath;
    }

    /**
     * Returns a new resource from the given subpath, concatenated with the rootpath.
     * E.g.: /home/nick/resources, where /home/nick is root and resources the subpath.
     *
     * @param subpath the path witihn the rootpath of this manager
     * @return a new URL to the resource or null if the resource doesn't exist
     */
    public URL getRsc(String subpath) {
        return getClass().getResource(root+fileSeparator+subpath);
    }

    /**
     * Returns an optional resource from the given subpath, concatenated with the rootpath.
     *
     * @see ResourceManager#getRsc(String)
     * @param subpath the path witihn the rootpath of this manager
     * @return an optional containing the url to the resource
     */
     public Optional<URL> getRscOptional(String subpath) {
       return NullHelpers.nullToOptional(() -> getRsc(subpath));
    }

    /**
     * Returns a new inputstream from the given subpath, concatenated with the rootpath.
     * E.g.: /home/nick/resources, where /home/nick is root and resources the subpath.
     *
     * @param subpath the path witihn the rootpath of this manager
     * @return a new InputStream from the resource
     */
    public InputStream getRscAsStream(String subpath) {
        return getClass().getResourceAsStream(root + fileSeparator + subpath);
    }

    /**
     * Returns an optional inputstream from the given subpath, concatenated with the rootpath.
     *
     * @see ResourceManager#getRscAsStream(String)
     * @param subpath the path witihn the rootpath of this manager
     * @return an optional InputStream from the resource
     */
    public Optional<InputStream> getRscStreamOptional(String subpath) {
        return NullHelpers.nullToOptional(() -> getRscAsStream(subpath));
    }

    /**
     * Tests if the given resource in this root + the subpath exists.
     *
     * @param subpath the subpath from this root directory
     * @return true if the resource exists, false otherwise
     */
    public boolean rscExists(String subpath) {
        return getRsc(subpath) != null;
    }

    /**
     * Returns the root-directory.
     *
     * @return the roo directory as string
     */
    public String getRoot() {
        return root;
    }
}
