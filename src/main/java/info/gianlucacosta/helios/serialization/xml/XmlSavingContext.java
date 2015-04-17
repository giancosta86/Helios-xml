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
import info.gianlucacosta.helios.serialization.SavingContext;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * Provides an OutputStream based on the XStream serialization library
 */
class XmlSavingContext implements SavingContext {

    private final XStream xStream;
    private final ObjectOutputStream outputStream;

    public XmlSavingContext(OutputStream outputStream, XStreamSerializationOptions serializationOptions) {
        xStream = serializationOptions.createXStream();
        try {
            if (serializationOptions.isCompressed()) {
                outputStream = new GZIPOutputStream(outputStream);
            }

            this.outputStream = xStream.createObjectOutputStream(new BufferedWriter(new OutputStreamWriter(outputStream)));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    @Override
    public void close() throws Exception {
        outputStream.close();
    }

}
