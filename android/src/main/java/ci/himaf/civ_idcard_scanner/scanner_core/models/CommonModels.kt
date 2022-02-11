package ci.himaf.civ_idcard_scanner.scanner_core.models

import com.google.mlkit.vision.text.Text

open class ScanFilterResult(val visionText: Text, val textBlockIndex: Int, val textBlock: Text.TextBlock, val data: ScanResultData)

class ScanResultData(val data: String, val elementType: CardElementType)

enum class CardElementType {
  cardNumber, expiryDate, cardFirstName, cardLastName
}

enum class CardHolderNameScanPositions(val value: String) {
  belowCardNumber("belowCardNumber"),
  aboveCardNumber("aboveCardNumber")
}

class CardNumberScanResult(visionText: Text, textBlockIndex: Int, textBlock: Text.TextBlock,
                           val cardNumber: String) : ScanFilterResult(visionText, textBlockIndex,
        textBlock, data = ScanResultData(cardNumber, CardElementType.cardNumber))

class ExpiryDateScanResult(visionText: Text, textBlockIndex: Int, textBlock: Text.TextBlock,
                           val expiryDate: String) : ScanFilterResult(visionText, textBlockIndex,
        textBlock, data = ScanResultData(expiryDate, CardElementType.expiryDate))

class CardFirstNameScanResult(visionText: Text, textBlockIndex: Int, textBlock: Text.TextBlock,
                              val cardFirstName: String) : ScanFilterResult(visionText, textBlockIndex,
        textBlock, data = ScanResultData(cardFirstName, CardElementType.cardFirstName))

class CardLastNameScanResult(visionText: Text, textBlockIndex: Int, textBlock: Text.TextBlock,
                               val cardLastName: String) : ScanFilterResult(visionText, textBlockIndex,
    textBlock, data = ScanResultData(cardLastName, CardElementType.cardLastName))

abstract class ScanFilter(val visionText: Text, val scannerOptions: CardScannerOptions) {
  abstract fun filter(): ScanFilterResult?
}