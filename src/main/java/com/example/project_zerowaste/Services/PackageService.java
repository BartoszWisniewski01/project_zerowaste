package com.example.project_zerowaste.Services;

import com.example.project_zerowaste.Repositories.PackageRepository;
import com.example.project_zerowaste.Entities.Package;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class PackageService {
    private PackageRepository packageRepository;
    private UserService userService;

    public void save(Package pack, String username) {
        pack.setUser(userService.findByUsername(username));
        packageRepository.save(pack);
    }

    public List<Package> findAll(String username) {
        return packageRepository.findAllByUserUsername(username);
    }

    public void deletePackageById(Long id) {
        packageRepository.deleteById(id);
    }

    public Package findById(Long id){
        return packageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package ID: " + id));
    }

    public void editPackage(Long id, Package updatedPackage) {
        Package pack = packageRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid package ID: " + id));

        pack.setName(updatedPackage.getName());
        pack.setExpiry_date(updatedPackage.getExpiry_date());

        packageRepository.save(pack);
    }
}
