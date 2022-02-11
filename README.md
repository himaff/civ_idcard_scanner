<h2 align="center">Fast, Accurate and Secure ivoirian card id scanner for Flutter </h2>

## Install

Add this to your package's pubspec.yaml file:

```yaml
dependencies:
  civ_idcard_scanner: <latest-version>
```
## Usage

Just import the package and call `scanCard`:

```dart
import 'package:civ_idcard_scanner/card_scanner.dart';
var cardDetails = await CardScanner.scanCard();

print(cardDetails);
```

---
