package ci.himaf.civ_idcard_scanner.logger

import android.util.Log
import ci.himaf.civ_idcard_scanner.scanner_core.models.CardScannerOptions

fun debugLog(message: String, scannerOptions: CardScannerOptions, tag: String = "card_scanner_debug_log") {
  if (scannerOptions.enableDebugLogs) {
    Log.d(tag, message)
  }
}