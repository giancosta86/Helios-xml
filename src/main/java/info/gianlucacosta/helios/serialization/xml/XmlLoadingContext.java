/*§
  ===========================================================================
  Helios - XML
  ===========================================================================
  Copyright (C) 2013-2015 Gianluca Costa
  ===========================================================================
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
  ===========================================================================
*/

package info.gianlucacosta.helios.serialization.xml;

import com.thoughtworks.xstream.XStream;
import info.gianlucacosta.helios.serialization.LoadingContext;

import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Provides an InputStream based on the XStream serialization library
 */
class XmlLoadingContext implements LoadingContext {

    private final XStream xStream;
    private final ObjectInputStream inputStream;

    public XmlLoadingContext(InputStream inputStream, XStreamSerializationOptions serializationOptions) {
        this.xStream = serializationOptions.createXStream();

        try {
            if (serializationOptions.isCompressed()) {
                inputStream = new GZIPInputStream(inputStream);
            }

            this.inputStream = xStream.createObjectInputStream(new BufferedReader(new InputStreamReader(inputStream)));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    @Override
    public ClassLoader getClassLoader() {
        return xStream.getClassLoader();
    }

    @Override
    public void setClassLoader(ClassLoader classLoader) {
        xStream.setClassLoader(classLoader);
    }

    @Override
    public void close() throws Exception {
        inputStream.close();
    }

}
