/**
 * Copyright (c) 2016 LARUS Business Automation [http://www.larus-ba.it]
 * <p>
 * This file is part of the "LARUS Integration Framework for Neo4j".
 * <p>
 * The "LARUS Integration Framework for Neo4j" is licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * Created on 18/02/16
 */
package it.neo4j.jdbc.bolt;

import it.neo4j.jdbc.ResultSet;
import it.neo4j.jdbc.bolt.data.ResultSetData;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.neo4j.driver.v1.ResultCursor;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

/**
 * @author AgileLARUS
 * @since 3.0.0
 */
public class BoltResultSetGettersTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @BeforeClass
    public static void initialize() {
        ResultSetData.initialize();
    }

    /*------------------------------*/
    /*          findColumn          */
    /*------------------------------*/
    @Test
    public void findColumnShouldReturnCorrectIndex() throws SQLException {
        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS, ResultSetData.RECORD_LIST_MORE_ELEMENTS);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        Assert.assertEquals(0, resultSet.findColumn("columnA"));
        Assert.assertEquals(1, resultSet.findColumn("columnB"));
    }

    @Test
    public void findColumnShouldThrowExceptionOnWrongLabel() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS, ResultSetData.RECORD_LIST_MORE_ELEMENTS);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.findColumn("columnZ");
    }

    // this test depends on the method close()
    @Ignore
    @Test
    public void findColumnShouldThrowExceptionOnClosedResultSet() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS, ResultSetData.RECORD_LIST_MORE_ELEMENTS);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.close();
        resultSet.findColumn("columnA");
    }

	/*------------------------------*/
    /*           getString          */
    /*------------------------------*/

    @Test
    public void getStringByLabelShouldReturnString() throws SQLException {
        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        Assert.assertEquals("value1", resultSet.getString("columnString"));

        resultSet.next();
        Assert.assertEquals("value2", resultSet.getString("columnString"));
    }

    @Test
    public void getStringByLabelShouldThrowExceptionNoLabel() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        resultSet.getString("columnZ");
    }

    //This test depends on the close method
    @Ignore
    @Test
    public void getStringByLabelShouldThrowExceptionClosed() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.close();
        resultSet.getString("columnString");
    }

    @Test
    public void getStringByIndexShouldReturnString() throws SQLException {
        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        Assert.assertEquals("value1", resultSet.getString(2));

        resultSet.next();
        Assert.assertEquals("value2", resultSet.getString(2));
    }

    @Test
    public void getStringByIndexShouldThrowExceptionNoIndex() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        resultSet.getString(99);
    }

    //This test depends on the close method
    @Ignore
    @Test
    public void getStringByIndexShouldThrowExceptionClosed() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.close();
        resultSet.getString(2);
    }

	/*------------------------------*/
	/*            getInt            */
	/*------------------------------*/

    @Test
    public void getIntByLabelShouldReturnInt() throws SQLException {
        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        Assert.assertEquals(1, resultSet.getInt("columnInt"));

        resultSet.next();
        Assert.assertEquals(2, resultSet.getInt("columnInt"));
    }

    @Test
    public void getIntByLabelShouldThrowExceptionNoLabel() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        resultSet.getInt("columnZ");
    }

    //This test depends on the close method
    @Ignore
    @Test
    public void getIntByLabelShouldThrowExceptionClosed() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.close();
        resultSet.getInt("columnInt");
    }

    @Test
    public void getIntByIndexShouldReturnInt() throws SQLException {
        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        Assert.assertEquals(1, resultSet.getInt(1));

        resultSet.next();
        Assert.assertEquals(2, resultSet.getInt(1));
    }

    @Test
    public void getIntByIndexShouldThrowExceptionNoIndex() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        resultSet.getInt(99);
    }

    //This test depends on the close method
    @Ignore
    @Test
    public void getIntByIndexShouldThrowExceptionClosed() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.close();
        resultSet.getInt(1);
    }

	/*------------------------------*/
	/*           getFloat           */
	/*------------------------------*/

    @Test
    public void getFloatByLabelShouldReturnFloat() throws SQLException {
        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        Assert.assertEquals(0.1F, resultSet.getFloat("columnFloat"), 0);

        resultSet.next();
        Assert.assertEquals(0.2F, resultSet.getFloat("columnFloat"), 0);
    }

    @Test
    public void getFloatByLabelShouldThrowExceptionNoLabel() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        resultSet.getFloat("columnZ");
    }

    //This test depends on the close method.
    @Ignore
    @Test
    public void getFloatByLabelShouldThrowExceptionClosed() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.close();
        resultSet.getFloat("columnFloat");
    }

    @Test
    public void getFloatByIndexShouldReturnFloat() throws SQLException {
        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        Assert.assertEquals(0.1F, resultSet.getFloat(3), 0);

        resultSet.next();
        Assert.assertEquals(0.2F, resultSet.getFloat(3), 0);
    }

    @Test
    public void getFloatByIndexShouldThrowExceptionNoIndex() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        resultSet.getFloat(99);
    }

    //This test depends on the close method
    @Ignore
    @Test
    public void getFloatByIndexShouldThrowExceptionClosed() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.close();
        resultSet.getFloat(3);
    }

	/*------------------------------*/
	/*            getShort          */
	/*------------------------------*/

    @Test
    public void getShortByLabelShouldReturnShort() throws SQLException {
        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        Assert.assertEquals(1, resultSet.getShort("columnShort"));

        resultSet.next();
        Assert.assertEquals(2, resultSet.getShort("columnShort"));
    }

    @Test
    public void getShortByLabelShouldThrowExceptionNoLabel() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        resultSet.getShort("columnZ");
    }

    //This test depends on the close method
    @Ignore
    @Test
    public void getShortByLabelShouldThrowExceptionClosed() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.close();
        resultSet.getShort("columnShort");
    }

    @Test
    public void getShortByIndexShouldReturnShort() throws SQLException {
        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        Assert.assertEquals(1, resultSet.getShort(1));

        resultSet.next();
        Assert.assertEquals(2, resultSet.getShort(1));
    }

    @Test
    public void getShortByIndexShouldThrowExceptionNoIndex() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.next();
        resultSet.getShort(99);
    }

    //This test depends on the close method
    @Ignore
    @Test
    public void getShortByIndexShouldThrowExceptionClosed() throws SQLException {
        expectedEx.expect(SQLException.class);

        ResultCursor resultCursor = ResultSetData.buildResultCursor(ResultSetData.KEYS_RECORD_LIST_MORE_ELEMENTS_MIXED, ResultSetData.RECORD_LIST_MORE_ELEMENTS_MIXED);
        ResultSet resultSet = new BoltResultSet(resultCursor);

        resultSet.close();
        resultSet.getShort(3);
    }
}