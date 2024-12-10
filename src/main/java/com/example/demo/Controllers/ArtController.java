package com.example.demo.Controllers;

import com.example.demo.Exceptions.CustomExceptions;
import com.example.demo.Exceptions.SimpleResponse;
import com.example.demo.Handlers.QueryHandlers.GetArt;
import com.example.demo.Models.Art;
import com.example.demo.DTO.ArtDTO;
import com.example.demo.DTO.CartDTO;
import com.example.demo.Repositories.ArtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/art")
public class ArtController {

    @Autowired private ArtRepository artRepository;
    @Autowired private GetArt getArt;
    @PostMapping("/singleArt")
    public ResponseEntity<ArtDTO> getArt(@RequestBody CartDTO cartDTO){
        return getArt.execute(cartDTO);
    }

    @GetMapping
    public ResponseEntity<List<Art>> getAllArts(){
        return ResponseEntity.ok(artRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> addArt(@RequestBody Art art){
        if(art.getId() == null)
            art.setId(UUID.randomUUID());
        if(art.getSoldOutArt() == null)
            art.setSoldOutArt(false);
        artRepository.save(art);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/addall")
    public ResponseEntity<Void> addCollectionOfArts(@RequestBody List<Art> arts){
        if(arts == null)
            throw new CustomExceptions(HttpStatus.BAD_REQUEST, new SimpleResponse("arts are not vaild"));
        artRepository.saveAll(arts);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateArt(@RequestBody Art art){
        artRepository.save(art);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArt(@PathVariable UUID id){
        artRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        artRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{cat}")
    public ResponseEntity<List<Art>> getArtOfCat(@PathVariable String cat){
        return ResponseEntity.ok(artRepository.findByCategory(cat));
    }
}
