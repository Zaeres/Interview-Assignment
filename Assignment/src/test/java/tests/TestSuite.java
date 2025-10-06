package tests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        BoardSetupTest.class,
        CardWorkflowTest.class,
        ChecklistTest.class
})
public class TestSuite
{

}

