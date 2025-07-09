package com.example.moneymate.controller;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.moneymate.model.Transaksi;
import com.example.moneymate.repository.TransaksiRepository;

@Controller
public class TransaksiController {

    @Autowired
    private TransaksiRepository transaksiRepository;

    // Halaman utama: redirect ke welcome
    @GetMapping("/")
    public String redirectToWelcome() {
        return "redirect:/welcome";
    }

    // Halaman welcome - selamat datang
    @GetMapping("/welcome")
    public String showWelcome() {
        return "welcome"; // templates/welcome.html
    }

    // Halaman dashboard: daftar transaksi + ringkasan
    @GetMapping("/dashboard")
    public String showTransaksi(Model model) {
        List<Transaksi> listTransaksi = transaksiRepository.findAll();

        // Total pemasukan
        double totalPemasukan = listTransaksi.stream()
                .filter(t -> "PEMASUKAN".equalsIgnoreCase(t.getJenis()))
                .mapToDouble(Transaksi::getJumlah)
                .sum();

        // Total pengeluaran
        double totalPengeluaran = listTransaksi.stream()
                .filter(t -> "PENGELUARAN".equalsIgnoreCase(t.getJenis()))
                .mapToDouble(Transaksi::getJumlah)
                .sum();

        // Ringkasan per kategori
        Map<String, Double> totalKategori = new LinkedHashMap<>();
        for (Transaksi t : listTransaksi) {
            String kategori = t.getKategori() != null ? t.getKategori() : "Lainnya";
            totalKategori.merge(kategori, t.getJumlah(), Double::sum);
        }

        // Format tanggal untuk ditampilkan
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (Transaksi t : listTransaksi) {
            if (t.getTanggal() != null) {
                t.setTanggalFormatted(sdf.format(t.getTanggal()));
            }
        }

        model.addAttribute("listTransaksi", listTransaksi);
        model.addAttribute("totalPemasukan", totalPemasukan);
        model.addAttribute("totalPengeluaran", totalPengeluaran);
        model.addAttribute("totalKategori", totalKategori);

        return "transaksi"; // templates/transaksi.html
    }

    // Form tambah transaksi
    @GetMapping("/transaksi/add")
    public String showFormTambah(Model model) {
        model.addAttribute("transaksi", new Transaksi());
        return "transaksi-form"; // templates/transaksi-form.html
    }

    // Simpan transaksi (baru/edit)
    @PostMapping("/simpan-transaksi")
    public String simpan(@ModelAttribute Transaksi transaksi) {
        transaksiRepository.save(transaksi);
        return "redirect:/dashboard";
    }

    // Form edit transaksi
    @GetMapping("/transaksi/edit/{id}")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Optional<Transaksi> optionalTransaksi = transaksiRepository.findById(id);
        if (optionalTransaksi.isPresent()) {
            model.addAttribute("transaksi", optionalTransaksi.get());
            return "transaksi-form";
        } else {
            return "redirect:/dashboard";
        }
    }

    // Hapus transaksi
    @GetMapping("/transaksi/delete/{id}")
    public String delete(@PathVariable Long id) {
        transaksiRepository.deleteById(id);
        return "redirect:/dashboard";
    }
}
