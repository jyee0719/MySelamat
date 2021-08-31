package my.edu.utar.myselamat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Essentials extends AppCompatActivity {

    Button btnMask1, btnMask2, btnMask3, btnSan1, btnSan2, btnSan3, btnShield, btnTempGun, btnGlove, btnOximeter, btnDisinfectant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_essentials);

        btnMask1 = findViewById(R.id.btnMask1);
        btnMask1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shopee.com.my/50-pcs-Face-Mask-Disposable-Earloop-3ply-3-ply-Anti-dust-Breathable-Civilian-Color-face-mask-Great-for-Protection-%E4%B8%80%E6%AC%A1%E6%80%A7%E5%8F%A3%E7%BD%A9-i.327978705.3005839258?position=4"));
                startActivity(intent);
            }
        });

        btnMask2 = findViewById(R.id.btnMask2);
        btnMask2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shopee.com.my/Premium-KF94%E3%80%904-PLY-MASK%E3%80%91(10PCS-PACK)-KOREA-MASK-PREMIUM-FACEMASK-THICK-BLACK-MASK%E5%8F%A3%E7%BD%A9-i.10933605.4520646409?position=4"));
                startActivity(intent);
            }
        });

        btnMask3 = findViewById(R.id.btnMask3);
        btnMask3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shopee.com.my/%F0%9F%93%A3Ready-Stock%F0%9F%93%A35-ply-N95-KN95-mask-%F0%9F%94%A5Individual-Packing-i.226798960.7430250687?position=0"));
                startActivity(intent);
            }
        });

        btnSan1 = findViewById(R.id.btnSan1);
        btnSan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shopee.com.my/Dettol-Hand-Sanitiser-(50ml)-02031092-i.12725966.101620768?position=5"));
                startActivity(intent);
            }
        });

        btnSan2 = findViewById(R.id.btnSan2);
        btnSan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shopee.com.my/Local-Stock%F0%9F%94%A5Spray-Gun-800ML-Wireless-Rechargeable-Disinfection-Sprayer-Nano-Blue-Ray-Atomizer-Fogging-Spray-Gun-%E8%93%9D%E5%85%89%E9%9B%BE%E5%8C%96%E6%B6%88%E6%AF%92%E6%A7%8D-i.417393869.2959979299?position=6"));
                startActivity(intent);
            }
        });

        btnSan3 = findViewById(R.id.btnSan3);
        btnSan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shopee.com.my/Fogging-Machine-Disinfectant-Spray-Gun-Handheld-Wireless-Atomizer-Fog-Blue-Light-Nano-Spray-Disinfectant-Liquid-Sanitize-i.2526572.8722880944?position=2"));
                startActivity(intent);
            }
        });

        btnShield = findViewById(R.id.btnShield);
        btnShield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shopee.com.my/%E3%80%90MALAYSIA-READY-STOCK%E3%80%91Transparent-Face-Shield-Protective-Mask-with-glasses-cover-Cooking-Protector-Face-Shield-%E7%9C%BC%E9%95%9C%E9%98%B2%E6%8A%A4%E9%9D%A2%E7%BD%A9-i.140193467.8008910869?position=0"));
                startActivity(intent);
            }
        });

        btnTempGun = findViewById(R.id.btnTempGun);
        btnTempGun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shopee.com.my/%F0%9F%94%A5%F0%9F%94%A5Ready-stock-in-KL(Fast-ship)-Sunphor-R6-Infrared-Thermometer-Gun-Temperature-Penembak-Suhu-Termometer-Medical-i.327846470.6178309430?position=21"));
                startActivity(intent);
            }
        });

        btnGlove = findViewById(R.id.btnGlove);
        btnGlove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shopee.com.my/%F0%9F%87%B2%F0%9F%87%BE100pcs-Disposable-Plastic-Gloves-AS-Hygiene-Restaurant-Home-Catering-Disposable-Plastic-Gloves-i.241094016.5534879905?position=0"));
                startActivity(intent);
            }
        });

        btnOximeter = findViewById(R.id.btnOximeter);
        btnOximeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shopee.com.my/Medical-Fingertip-Pulse-Oximeter-Pulso-Oximetro-Home-family-Pulse-Oxymeter-Pulsioximetro-finger-pulse-oximeter-i.417443540.5188963944?position=8"));
                startActivity(intent);
            }
        });

        btnDisinfectant = findViewById(R.id.btnDisinfectant);
        btnDisinfectant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://shopee.com.my/%F0%9F%9A%9AREADY-STOCK%F0%9F%9A%9A-5L-Disinfectant-Liquid-KLEEN-PRO-NANO-MIST-FOGGING-SPRAY-%F0%9F%94%A5Alcohol-Free%F0%9F%94%A5Disinfectant-Multi-Purpose%F0%9F%94%A5-i.144486535.6845839211?position=0"));
                startActivity(intent);
            }
        });
    }
}