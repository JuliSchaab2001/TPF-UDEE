package com.utn.TPFUDEE.Controllers;



import com.utn.TPFUDEE.Services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillController {

    private static final String BILL_PATH = "bill";

    @Autowired
    private BillService billService;

    /*@GetMapping("/{id}")
    public ResponseEntity<Bill> getById(@PathVariable Integer id) throws BillNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).header("Nombre", "Cuerpo").body(billService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity add(@RequestBody Bill bill) throws BillExistException {
        return ResponseEntity.status(HttpStatus.CREATED).location(EntityURLBuilder.buildURL(BILL_PATH, billService.add(bill).getBill_id())).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Integer id) throws BillNotFoundException {
        billService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).header("Aca", "Salio todo en orden man").build();
    }* PUEDE QUE ESTE SI POR ALGUNA EXTRAÑA RAZON QUE QUIERA BORRAR UNA FACTURA/*/

}
