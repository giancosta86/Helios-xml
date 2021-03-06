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

/**
 * Provides the options required to instantiate the XStream-based serialization
 * service and its contexts - for example, how to create the underlying XStream
 * object itself, or if the XML is to be compressed.
 */
public interface XStreamSerializationOptions {

    XStream createXStream();

    boolean isCompressed();

}
