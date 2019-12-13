/*! ******************************************************************************
 *
 * Pentaho Data Integration
 *
 * Copyright (C) 2002-2018 by Hitachi Vantara : http://www.pentaho.com
 *
 *******************************************************************************
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 ******************************************************************************/

package org.apache.hop.ui.core.database.dialog;

import java.util.ArrayList;
import java.util.List;

import org.apache.hop.ui.hopui.HopUi;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.apache.hop.core.Const;
import org.apache.hop.core.database.DatabaseMeta;
import org.apache.hop.core.database.DatabaseTestResults;
import org.apache.hop.core.util.StringUtil;
import org.apache.hop.i18n.BaseMessages;
import org.apache.hop.ui.core.dialog.ShowMessageDialog;

/**
 *
 * Dialog that allows you to edit the settings of a database connection.
 *
 * @see <code>DatabaseMeta</code>
 * @author Matt
 * @since 18-05-2003
 *
 */
public class DatabaseDialog extends DatabaseMetaDialog {
  private static Class<?> PKG = DatabaseDialog.class; // for i18n purposes, needed by Translator2!!

  public DatabaseDialog( Shell parent ) {
    super( parent, new DatabaseMeta(  ) );
  }

  public DatabaseDialog( Shell parent, DatabaseMeta databaseMeta ) {
    super( parent, databaseMeta );
  }

  public String open() {
    return super.open();
  }

  public static void showDatabaseExistsDialog( Shell parent, DatabaseMeta databaseMeta ) {
    String title = BaseMessages.getString( PKG, "DatabaseDialog.DatabaseNameExists.Title" );
    String message = BaseMessages.getString( PKG, "DatabaseDialog.DatabaseNameExists", databaseMeta.getName() );
    String okButton = BaseMessages.getString( PKG, "System.Button.OK" );
    MessageDialog dialog =
      new MessageDialog( parent, title, null, message, MessageDialog.ERROR, new String[] { okButton }, 0 );
    dialog.open();
  }

  @Deprecated
  public void setModalDialog(boolean modal) {
    // Ignore
  }

  public void setDatabases( List<DatabaseMeta> databases) {
    // Ignore
  }
}
