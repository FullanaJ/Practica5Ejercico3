package api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;


@RestController
@RequestMapping("api")
public class ProductController {
    @Autowired
    private IProductService productService;
    /**
     * Obtiene Fechas
     * @return
     */
    @GetMapping(value = "/date")
    public ResponseEntity<Set<Date>> getDate() {
        Set<Date> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Obtiene la fecha del sistema y suma n días
     * @param n
     * @return
     */
    @GetMapping(value = "/date/{n}")
    public ResponseEntity getDay(@PathVariable String n) {
        LocalDate now = LocalDate.now().plusDays(Integer.parseInt(n));
        Date date = new Date(now.getDayOfMonth(), now.getMonthValue(), now.getYear());
        return new ResponseEntity(date, HttpStatus.OK);
    }

    /**
     * Cambia la fecha del sistema
     * Funciona solo con Windows
     * @param date
     */
    @PostMapping(value = "/date")
    public void cambiaDate(@RequestBody Date date) {
        String comando = "cmd.exe";
        String nuevaFecha = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
        String entrada = "date " + nuevaFecha;

        try {
            ProcessBuilder builder = new ProcessBuilder(comando, "/c", entrada);
            Process proceso = builder.start();
            proceso.waitFor();
            System.out.println("Fecha cambiada exitosamente a: " + nuevaFecha);
        } catch (IOException ex) {
            System.out.println("Error de I/O: " + ex);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

}