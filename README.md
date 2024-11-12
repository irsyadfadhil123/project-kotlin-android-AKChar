# Pendahuluan:
Project ini dibuat untuk belajar membuat **Aplikasi Android** sederhana menggunakan bahasa pemrograman **Kotlin** sekaligus untuk memenuhi Submission dari **Dicoding** di kelas [Belajar Membuat Aplikasi Android untuk Pemula](https://www.dicoding.com/academies/51-belajar-membuat-aplikasi-android-untuk-pemula).
Kriteria, Penilaian, serta Tips dan Trik dibawah ini sepenuhnya milik **Dicoding**

# Kriteria:
Fitur yang harus ada pada aplikasi:

## 1. Halaman Utama
Syarat:
- Menampilkan gambar dalam format List dengan jumlah minimal 10 item yang berbeda. Gunakanlah RecyclerView untuk menyusun listnya. Informasi yang ditampilkan mengandung :
  - Judul dari artikel
  - Memaparkan overview dari artikel (opsional)
- Memunculkan halaman detail ketika salah satu item ditekan. Anda bisa menerapkan handler click pada setiap item RecyclerView
## 2. Halaman Detail
Syarat:
- Menampilkan gambar dan informasi yang relevan pada halaman detail. 
- Informasi yang relevan mencakup kesamaan informasi yang ditampilkan pada halaman utama dengan halaman detail
  - Terdapat judul dan gambar yang sesuai dengan list
  - Terdapat kalimat deskripsi yang lebih panjang atau informasi tambahan lainnya
- Gunakan ScrollView supaya konten tetap dapat dilihat ketika aplikasi dirotasi.

## 3. Halaman About
Syarat:
- Menampilkan foto diri, nama, dan email yang terdaftar di Dicoding.
- Dalam mengakses halaman about, pastikan terdapat tombol yang bisa digunakan untuk mengakses halamannya. Untuk cara mengaksesnya, kamu bisa mengimplementasikan :
  - Dengan menambahkan elemen View khusus (bisa option menu, tombol, atau tab) yang mengandung ID “about_page”

Berikut kerangka tampilan yang bisa Anda gunakan sebagai referensi:
![Ref]()

# Penilaian:
Submission Anda akan dinilai oleh reviewer dengan skala 1-5 berdasarkan dari parameter yang ada.
Anda dapat menerapkan beberapa saran di bawah ini untuk mendapatkan nilai tinggi, berikut sarannya:
- Menerapkan tampilan aplikasi yang sesuai standar. Di mana kriterianya adalah :
  - Memiliki width, height, margin, dan padding yang sesuai.
  - Komponen tidak saling bertumpuk
  - Penggunaan komponen sesuai dengan fungsinya
  - Penggunaan warna yang sesuai
  - Semua data dapat terlihat baik ketika landscape maupun potrait (gunakan ScrollView)
- Informasi yang ditampilkan pada halaman detail lebih lengkap.
  - Jenis data yang ditampilkan bervariasi
- Membuat custom splash screen yang menarik.
  - Pastikan nama Activity yang digunakan mengandung kata “Splash”
  - Gunakan referensi [berikut](https://developer.android.com/develop/ui/views/launch/splash-screen) untuk menerapkan splash screen pada project sesuai dengan yang disarankan.
- Menggunakan CardView yang sesuai untuk menampilkan konten List.
  - Gunakan referensi [CardView Guideline](https://m2.material.io/components/cards#anatomy) untuk menerapkan CardView yang baik dan benar.
- Mengimplementasikan fungsi Share pada halaman detail.
  - Pastikan pada tombol Share menggunakan id “action_share”

# Tips dan Trik:
Ketika mengerjakan submission, mungkin Anda mengalami kendala. Oleh karena itu, kami mengumpulkan beberapa kendala yang sering ditemui siswa-siswa lain. Agar kendala tersebut tidak terulang kepada Anda, kami telah mengumpulkan tips untuk menanggulanginya.
## Beberapa Tips untuk Membuat Submission
- Sebagai latihan, Anda bisa menggunakan project list Pahlawan yang sudah Anda pelajari sebelumnya. Bedanya, pada latihan tersebut belum ada aksi untuk membuka halaman detail dari masing-masing item. Itulah tantangan yang perlu Anda eksplorasi.
- Untuk bisa membuka halaman detail ketika salah satu item diklik, Anda harus menggunakan `Intent` ketika terdapat aksi klik. Anda bisa mengirimkan data satu per satu maupun menggunakan `Parcelable`. Namun, kami merekomendasikan menggunakan `Parcelable` yang lebih efisien.
### Kotlin
```kotlin
// build.gradle (module: app)
plugins{
  id 'com.android.application'
  id 'kotlin-android'
  id 'kotlin-parcelize'
}

// data class
import kotlinx.parcelize.Parcelize
@Parcelize
data class Hero(
    var name: String,
    var description: String,
    var photo: Int
) : Parcelable
```

### Java
```java
public class Hero implements Parcelable {
    private String name;
    private String description;
    private Integer photo;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public Integer getPhoto() {
        return photo;
    }
 
    public void setPhoto(Integer photo) {
        this.photo = photo;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeInt(this.photo);
    }
    
    Hero() {
    }
    
    private Hero(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.photo = in.readInt();
    }
    
    public static final Parcelable.Creator<Hero> CREATOR = new Parcelable.Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel source) {
            return new Hero(source);
        }
        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}
```

Selanjutnya, ada banyak cara untuk menangani aksi klik, yaitu menggunakan *interface* dan dieksekusi di dalam `Activity` seperti pada latihan atau bagi pengguna Kotlin bisa menggunakan *lambda* seperti yang ada pada bedah kode. Namun, di sini akan ditunjukkan cara untuk berpindah halaman yang paling simpel, yakni langsung memanggil `Intent` di dalam `Adapter` seperti ini.

### Kotlin
```kotlin
override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
    val (name, description, photo) = listHero[position]
    holder.imgPhoto.setImageResource(photo)
    holder.tvName.text = name
    holder.tvDescription.text = description

    holder.itemView.setOnClickListener {
        val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
        intentDetail.putExtra("key_hero", listHero[holder.adapterPosition])
        holder.itemView.context.startActivity(intentDetail)       
    }
}
```

### Java
```java
@Override
public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
    Hero hero = listHero.get(position);
    holder.imgPhoto.setImageResource(hero.getPhoto());
    holder.tvName.setText(hero.getName());
    holder.tvDescription.setText(hero.getDescription());

    holder.itemView.setOnClickListener(v -> {
        Intent intentDetail = new Intent(holder.itemView.getContext(), DetailActivity.class);
        intentDetail.putExtra("key_hero", listHero.get(holder.getAdapterPosition()));
        holder.itemView.getContext().startActivity(intentDetail); 
    });
}
```

Kemudian, terima datanya menggunakan `getParcelableExtra` di halaman detail seperti yang sudah Anda pelajari sebelumnya.

### Kotlin
```kotlin
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataHero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Person>(key_hero, Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Person>(key_hero)
        }
        ...
    }
}
```

### Java
```java
public class DetailActivity extends AppCompatActivity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
 
        Hero dataHero = getIntent().getParcelableExtra(key_hero);
        ...    
    }
}
```

Langkah terakhir, Anda cukup membuat desain halaman detail dan menampilkan datanya. Anda bisa memanfaatkan `ViewBinding` ataupun menggunakan `findViewById` satu per satu seperti ini.

### Kotlin
```kotlin
val tvDetailName = findViewById(R.id.tv_detail_name)
val tvDetailDescription = findViewById(R.id.tv_detail_description)
val ivDetailPhoto = findViewById(R.id.iv_detail_photo)

tvDetailName.text = dataHero.name
tvDetailDescription.text = dataHero.description
ivDetailPhoto.setImageResource(dataHero.photo)
```

### Java
```java
TextView tvDetailName = findViewById(R.id.tv_detail_name);
TextView tvDetailDescription = findViewById(R.id.tv_detail_description);
ImageView ivDetailPhoto = findViewById(R.id.iv_detail_photo);

tvDetailName.setText(dataHero.getName());
tvDetailDescription.setText(dataHero.getDescription());
ivDetailPhoto.setImageResource(dataHero.getPhoto());
```
