//
//  Extension.swift
//  iosApp
//
//  Created by Developer on 29/11/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI

extension String{
    func asUrl()->URL{
        var url = URL(string: "https://")
        if (!self.isEmpty){ url = URL(string: self)}
        return url!
    }
}

extension UIImage {
    enum JPEGQuality: CGFloat {
        case lowest  = 0
        case low     = 0.25
        case medium  = 0.5
        case high    = 0.75
        case highest = 1
    }
    
    /// Returns the data for the specified image in JPEG format.
    /// If the image object’s underlying image data has been purged, calling this function forces that data to be reloaded into memory.
    /// - returns: A data object containing the JPEG data, or nil if there was a problem generating the data. This function may return nil if the image has no data or if the underlying CGImageRef contains data in an unsupported bitmap format.
    func jpeg(_ quality: JPEGQuality) -> Data? {
        return self.jpegData(compressionQuality: quality.rawValue)
    }
    
    func isImage()->Bool{
        let cgref = self.cgImage
        let cim = self.ciImage
        return cim != nil || cgref != nil
    }
}
