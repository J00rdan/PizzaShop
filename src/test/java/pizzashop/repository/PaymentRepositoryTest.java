package pizzashop.repository;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PaymentRepositoryTest {

    PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    @DisplayName("ecp1")
    @Tag("ECP")
    @Order(1)
    void ecp1(){
        Payment payment = new Payment(1, PaymentType.Cash, 10);
        int count = paymentRepository.getAll().size();

        try{
            paymentRepository.add(payment);
            assertEquals(count + 1, paymentRepository.getAll().size());
        } catch (Exception e){
            assert false;
        }
    }

    @Test
    @DisplayName("ecp2")
    @Tag("ECP")
    @Order(2)
    void ecp2(){
        Payment payment = new Payment(2, PaymentType.Card, -10);

        try{
            paymentRepository.add(payment);
            assert false;
        } catch (Exception e){
            assertEquals("negative amount", e.getMessage());
        }
    }

    @Test
    @DisplayName("ecp3")
    @Tag("ECP")
    @Order(3)
    void ecp3(){
        Payment payment = new Payment(-5, PaymentType.Card, 10);

        try{
            paymentRepository.add(payment);
            assert false;
        } catch (Exception e){
            assertEquals("invalid table", e.getMessage());
        }
    }

    @Test
    @DisplayName("bva1")
    @Tag("BVA")
    @Order(4)
    void bva1(){
        Payment payment = new Payment(5, PaymentType.Card, 0.5);
        int count = paymentRepository.getAll().size();

        try{
            paymentRepository.add(payment);
            assertEquals(count + 1, paymentRepository.getAll().size());
        } catch (Exception e){
            assert false;
        }
    }

    @Test
    @DisplayName("bva2")
    @Tag("BVA")
    @Order(5)
    void bva2(){
        Payment payment = new Payment(5, PaymentType.Card, 0);

        try{
            paymentRepository.add(payment);
            assert false;
        } catch (Exception e){
            assertEquals("negative amount", e.getMessage());
        }
    }

    @Test
    @DisplayName("bva3")
    @Tag("BVA")
    @Order(6)
    void bva3(){
        Payment payment = new Payment(1, PaymentType.Card, 120);
        int count = paymentRepository.getAll().size();

        try{
            paymentRepository.add(payment);
            assertEquals(count + 1, paymentRepository.getAll().size());
        } catch (Exception e){
            assert false;
        }
    }

    @Test
    @DisplayName("bva4")
    @Tag("BVA")
    @Order(7)
    void bva4(){
        Payment payment = new Payment(9, PaymentType.Card, 150);

        try{
            paymentRepository.add(payment);
            assert false;
        } catch (Exception e){
            assertEquals("invalid table", e.getMessage());
        }
    }

}