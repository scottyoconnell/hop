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

package org.apache.hop.pipeline.transforms.setvalueconstant;

import junit.framework.Assert;
import org.apache.hop.core.logging.LoggingObjectInterface;
import org.apache.hop.core.row.RowMeta;
import org.apache.hop.core.row.IValueMeta;
import org.apache.hop.core.row.value.ValueMetaString;
import org.apache.hop.pipeline.transforms.mock.TransformMockHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Collections;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Tests for "Set field value to a constant" transform
 *
 * @author Pavel Sakun
 * @see SetValueConstant
 */
public class SetValueConstantTest {
  private TransformMockHelper<SetValueConstantMeta, SetValueConstantData> smh;

  @Before
  public void setUp() {
    smh =
      new TransformMockHelper<SetValueConstantMeta, SetValueConstantData>( "SetValueConstant", SetValueConstantMeta.class,
        SetValueConstantData.class );
    when( smh.logChannelFactory.create( any(), any( LoggingObjectInterface.class ) ) ).thenReturn(
      smh.logChannelInterface );
  }

  @After
  public void cleanUp() {
    smh.cleanUp();
  }

  @Test
  public void testUpdateField() throws Exception {
    SetValueConstant transform = new SetValueConstant( smh.transformMeta, smh.iTransformMeta, smh.iTransformData, 0, smh.pipelineMeta, smh.pipeline );

    IValueMeta valueMeta = new ValueMetaString( "Field1" );
    valueMeta.setStorageType( IValueMeta.STORAGE_TYPE_BINARY_STRING );

    RowMeta rowMeta = new RowMeta();
    rowMeta.addValueMeta( valueMeta );

    SetValueConstantMeta.Field field = new SetValueConstantMeta.Field();
    field.setFieldName( "Field Name" );
    field.setEmptyString( true );
    field.setReplaceMask( "Replace Mask" );
    field.setReplaceValue( "Replace Value" );

    doReturn( Collections.singletonList( field ) ).when( smh.initTransformMetaInterface ).getFields();
    doReturn( field ).when( smh.initTransformMetaInterface ).getField( 0 );
    doReturn( rowMeta ).when( smh.initTransformDataInterface ).getConvertRowMeta();
    doReturn( rowMeta ).when( smh.initTransformDataInterface ).getOutputRowMeta();
    doReturn( 1 ).when( smh.initTransformDataInterface ).getFieldnr();
    doReturn( new int[] { 0 } ).when( smh.initTransformDataInterface ).getFieldnrs();
    doReturn( new String[] { "foo" } ).when( smh.initTransformDataInterface ).getRealReplaceByValues();

    transform.init();

    Method m = SetValueConstant.class.getDeclaredMethod( "updateField", Object[].class );
    m.setAccessible( true );

    Object[] row = new Object[] { null };
    m.invoke( transform, new Object[] { row } );

    Assert.assertEquals( "foo", valueMeta.getString( row[ 0 ] ) );
  }
}