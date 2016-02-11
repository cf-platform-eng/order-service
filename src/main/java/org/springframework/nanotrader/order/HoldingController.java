package org.springframework.nanotrader.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/holdings")
public class HoldingController {

    @Autowired
    HoldingRepository holdingRepository;

    @RequestMapping("/count")
    public long count() {
        return holdingRepository.count();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Holding findById(@PathVariable Long id) {
        return holdingRepository.findOne(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public Holding save(@RequestBody Holding holding) {
        return holdingRepository.save(holding);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public void delete(@RequestBody Holding holding) {
        holdingRepository.delete(holding);
    }

}
