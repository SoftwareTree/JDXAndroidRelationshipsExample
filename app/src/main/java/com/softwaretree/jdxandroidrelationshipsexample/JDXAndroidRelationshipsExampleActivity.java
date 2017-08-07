package com.softwaretree.jdxandroidrelationshipsexample;

import java.util.ArrayList;
import java.util.List;

import com.softwaretree.jdx.JDXHelper;
import com.softwaretree.jdx.JDXS;
import com.softwaretree.jdx.JDXSetup;
import com.softwaretree.jdx.ObjectId;
import com.softwaretree.jdxandroid.Utils;
import com.softwaretree.jdxandroidrelationshipsexample.model.SimpleCompany;
import com.softwaretree.jdxandroidrelationshipsexample.model.SimpleEmp;
import com.softwaretree.jx.JXResource;
import com.softwaretree.jx.JXSession;
import com.softwaretree.jx.JXUtilities;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This project exemplifies how JDXA ORM and associated utilities can be used to easily develop 
 * an Android application that exchanges data of domain model objects with an SQLite database.
 * In particular, this project demonstrates the following:
 * <p>
 * 1) How an ORM Specification (mapping) for domain model classes can be defined textually using
 * simple statements.  The mapping is specified in a text file \res\raw\relationships_example.jdx identified 
 * by the resource id R.raw.relationships_example.
 * <p>
 * 2) The object model used by this application has one-to-one and one-to-many relationships.  
 * The mapping file shows how such relationships can easily be specified declaratively. The mapping specification
 * also illustrates the use of JDX_OBJECT_MODEL_PACKAGE specification that helps in further simplifying
 * the mapping specification by using the dotted notation for class names that avoids the need for
 * otherwise mentioning the fully qualified class names including the package names. 
 * <p>
 * 3) Use of {@link AppSpecificJDXSetup} and {@link MyDatabaseAndJDX_Initializer} classes to easily: 
 *   <br>&nbsp;&nbsp;&nbsp;&nbsp;  a) create the underlying database, if not already present. 
 *   <br>&nbsp;&nbsp;&nbsp;&nbsp;  b) create the schema (tables and constraints) corresponding to the JDXA ORM specification 
 *      every time the application runs. See setForceCreateSchema(true) in {@link AppSpecificJDXSetup#initialize()}. 
 *   <br>&nbsp;&nbsp;&nbsp;&nbsp;  c) populate the schema with application objects data. 
 * <p>
 * 4) Example of using IMPLICIT_ATTRIB specifications in the mapping file such that,
 * based on a defined RELATIONSHIP, the value of primitive attribute can be implicitly 
 * initialized by JDX from a corresponding primitive value in a related object.
 * <p>
 * 5) Example of JDX APIs to easily interact with relational data with just a few lines 
 * of object-oriented code that does not involve writing/processing any SQL statements.
 * <p>  
 * 6) Examples of deep, shallow, named and directed queries.
 * <p>  
 * 7) Examples of how details of an object or a list of objects can be added in JDX log and 
 * how that output can be collected in a file and then displayed in a scrollable TextBox.
 * <p>
 * @author Damodar Periwal
 */
public class JDXAndroidRelationshipsExampleActivity extends Activity {
    private JDXSetup jdxSetup = null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.setTitle(getResources().getString(R.string.activity_title));
        
        TextView tvJDXLog = (TextView) findViewById(R.id.tvJDXLog);
        tvJDXLog.setMovementMethod(new ScrollingMovementMethod());

        try {
            AppSpecificJDXSetup.initialize();  // must be done before calling getInstance()
            jdxSetup = AppSpecificJDXSetup.getInstance(this);
            
            // Use a JDXHelper object to easily configure capturing of JDX log output 
            JDXHelper jdxHelper = new JDXHelper(jdxSetup);
            String jdxLogFileName = getFilesDir() + System.getProperty("file.separator") + "jdx.log";
            jdxHelper.setJDXLogging(jdxLogFileName);
    		
            useJDXORM(jdxSetup);
            
            jdxHelper.resetJDXLogging();
            
            // Show the captured JDX log on the screen
            tvJDXLog.setText(Utils.getTextFileContents(jdxLogFileName));
        } catch (Exception ex) {
            Toast.makeText(getBaseContext(), "Exception: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
            cleanup();
            return;
        }     
    }
    
    /**
     * Do the necessary cleanup.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        cleanup();
    }
    
    private void cleanup() {
        AppSpecificJDXSetup.cleanup(); // Do this when the application is exiting.
        jdxSetup = null;
    }
    
    /**
     * Illustrates code using some JDXA ORM API calls.
     * 
     * @param jdxSetup
     */
    private void useJDXORM(JDXSetup jdxSetup) throws Exception {   
        if (null == jdxSetup) {
            return;
        }
              
        // Obtain ORM handles.
        JXResource jxResource = jdxSetup.checkoutJXResource();
        JXSession jxSessionHandle = jxResource.getJXSessionHandle();
        JDXS jdxHandle = jxResource.getJDXHandle();
        
        String companyClassName = SimpleCompany.class.getName();
        String employeeClassName = SimpleEmp.class.getName();

        try {      
            String companyId = "C1";
            
            // ****************************************************************
            // Retrieve the SimpleCompany C1 with a shallow query
            // ****************************************************************
            JXUtilities.log("\n-- Doing a shallow query for the SimpleCompany C1 --\n");
            List queryResults = jdxHandle.query(companyClassName, "companyId=" + "'" + companyId + "'", JDXS.ALL,
                    JDXS.FLAG_SHALLOW, null);
            JXUtilities.printQueryResults(queryResults);
            SimpleCompany company = (SimpleCompany) queryResults.get(0);
            
    		// ***************************************************************
    		// Retrieve (lazy fetch) foreignLocations of the above company that was retrieved in a shallow way
            // There should be two foreign locations associated to this company         
    		// ****************************************************************
            JXUtilities.log("\n-- Accessing (lazy fetch) the foreignLocations objects of the C1 company --\n");
    		queryResults = jdxHandle.access(company, "foreignLocations", JDXS.ALL, 0, null);
    		JXUtilities.printQueryResults(queryResults); 
            
            // ****************************************************************
            // Retrieve the SimpleCompany C1 with a deep query
            // ****************************************************************
            JXUtilities.log("\n-- Doing a deep query for the SimpleCompany C1 --\n");
            queryResults = jdxHandle.query(companyClassName, "companyId=" + "'" + companyId + "'", JDXS.ALL,
                    JDXS.FLAG_DEEP, null);
            JXUtilities.printQueryResults(queryResults);
            
            // ****************************************************************
            // Retrieve all the SimpleEmp objects with a deep query
            // ****************************************************************
            JXUtilities.log("\n-- Doing a deep query for all the SimpleEmployee objects --\n");
            queryResults = jdxHandle.query(employeeClassName, null, JDXS.ALL, JDXS.FLAG_DEEP, null);
            JXUtilities.printQueryResults(queryResults);
            
            // ****************************************************************
            // Retrieve a SimpleEmp (E1) object using its object id with a shallow query
            // ****************************************************************  
            JXUtilities.log("\n-- getObjectById for empId=E1 --\n");
            ObjectId oid = ObjectId.createObjectId(employeeClassName + ";empId=E1");
            SimpleEmp emp = (SimpleEmp) jdxHandle.getObjectById(oid, true, JDXS.FLAG_SHALLOW, null);
            JXUtilities.printObject(emp, 0, null); 
            
            // ****************************************************************
            // Retrieve (lazy fetch) the address object of the E1 employee that was retrieved with a shallow query
            // ****************************************************************  
            JXUtilities.log("\n-- Accessing (lazy fetch) address object for employee E1 --\n");
            queryResults = jdxHandle.access(emp, "address", 1, 0, null);
            JXUtilities.printQueryResults(queryResults);
                    
            // ****************************************************************
            // Retrieve a SimpleEmp (E2) object using its object id using a deep query
            // ****************************************************************  
            JXUtilities.log("\n-- getObjectById for empId=E2 --\n");
            oid = ObjectId.createObjectId(employeeClassName + ";empId=E2");
            emp = (SimpleEmp) jdxHandle.getObjectById(oid, true, JDXS.FLAG_DEEP, null);
            JXUtilities.printObject(emp, 0, null); 
            
            // ****************************************************************
            // Update a SimpleEmp (E2) object and re-fetch it using its object id
            // ****************************************************************  
            JXUtilities.log("\n-- Update SimpleEmp(E2) title and retrieve it again --\n");
            emp.setTitle("New Title");
            jdxHandle.update(emp, JDXS.FLAG_SHALLOW, null);
            emp = (SimpleEmp) jdxHandle.getObjectById(oid, true, JDXS.FLAG_DEEP, null);
            JXUtilities.printObject(emp, 0, null);
            
            // ****************************************************************
            // Retrieve all the managers using a named query "GetEmpByTitle" 
            // defined in the mapping specification
            // ****************************************************************
            JXUtilities.log("\n-- Retrieving Managers using the named query GetEmpByTitle --\n");
            List qParams = new ArrayList();
            qParams.add("Manager"); 
            queryResults = jdxHandle.executeNamedQuery("GetEmpByTitle", employeeClassName, qParams, JDXS.ALL, JDXS.FLAG_SHALLOW, null);
            JXUtilities.printQueryResults(queryResults);
            
            // ****************************************************************
            // Now retrieve all the consultants using the named query "GetEmpByTitle" 
            // defined in the mapping specification
            // ****************************************************************
            JXUtilities.log("\n-- Retrieving Consultants using the named query GetEmpByTitle --\n");
            qParams = new ArrayList();
            qParams.add("Consultant"); 
            queryResults = jdxHandle.executeNamedQuery("GetEmpByTitle", employeeClassName, qParams, JDXS.ALL, JDXS.FLAG_SHALLOW, null);
            JXUtilities.printQueryResults(queryResults);
            
            // ****************************************************************
            // Retrieve all SimpleEmp objects with a deep query but don't fetch 
            // the associated SimpleDept objects with them.
            // This is an example of directed operations.
            // ****************************************************************  
            JXUtilities.log("\n-- Deep query for all the SimpleEmp objects without the associated SimpleDept objects --\n");
            List queryDetails = new ArrayList();
                     
            List ignoreList = new ArrayList();
            ignoreList.add(employeeClassName);
            ignoreList.add("dept");
            
            List option1 = new ArrayList();
            option1.add(JDXS.IGNORE);
            option1.add(ignoreList);
            
            queryDetails.add(option1);
            
            queryResults = jdxHandle.query(employeeClassName, null, JDXS.ALL, JDXS.FLAG_DEEP, queryDetails);
            JXUtilities.printQueryResults(queryResults);
            
        } catch (Exception ex) {
            System.out.println("JDX Error " + ex.getMessage());
            Log.e("JDX", "Error", ex);
            throw ex;
        }   finally {
            jdxSetup.checkinJXResource(jxResource);
        }
    }
}