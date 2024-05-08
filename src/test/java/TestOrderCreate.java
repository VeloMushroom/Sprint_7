import io.restassured.response.ValidatableResponse;
import helpers.OrderChecks;
import helpers.OrderClient;
import pojo.OrderCreate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class TestOrderCreate {

    private final OrderClient client = new OrderClient();
    private final OrderChecks check = new OrderChecks();

    private final String firstName;
    private final String lastName;
    private final String address;
    private final int metroStation;
    private final String phone;
    private final int rentTime;
    private final String deliveryDate;
    private final String comment;
    private final String[] color;

    public TestOrderCreate(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, String[] color) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderData() {
        return new Object[][]{
                {"имя", "фамилия", "адрес", 0, "01234567890", 1, "2024-06-10", "комментарий", new String[]{"BLACK"}},
                {"имя0", "фамилия0", "адрес0", 1, "01234567891", 2, "2024-06-11", "комментарий0", new String[]{"GRAY"}},
                {"имя1", "фамилия1", "адрес1", 2, "01234567892", 3, "2024-06-12", "комментарий1", new String[]{"BLACK", "GRAY"}},
                {"имя2", "фамилия2", "адрес2", 3, "01234567893", 4, "2024-06-13", "комментарий2", new String[]{}}
        };
    }

    @Test
    public void createOrderTest(){
        OrderCreate orderCreate = new OrderCreate(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);
        ValidatableResponse createResponse=client.createOrder(orderCreate);
        int orderTrack=check.createSuccessOrder(createResponse);
        assertNotEquals(0, orderTrack);
    }
}
