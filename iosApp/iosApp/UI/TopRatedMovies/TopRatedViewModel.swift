//
//  TopRatedViewModel.swift
//  iosApp
//
//  Created by Annas Surdyanto on 29/11/21.
//  Copyright © 2021 private project. All rights reserved.
//

import Foundation
import shared

class TopRatedViewModel: ObservableObject{
    private let sharedViewModel = MovieModule().movieSharedViewModel
    private let movieExtension = MovieType.shared
    @Published var data = [Movie]()
    var errorMessage: String? = nil
    var listIsFull: Bool = false
    var pageSize: Int32 = 0
    var currentPage: Int32 = 0
    var loadingPage = false
    
    func initState() {
        errorMessage = nil
        loadingPage = true
        sharedViewModel.loadMovie(url: movieExtension.url(type: movieExtension.TOP_RATED_MOVIES), page: currentPage+1).collectCommon(){[self] result in
            loadingPage = false
            errorMessage = result?.error != nil ? result?.error?.message : result?.movieResponse?.message
            pageSize = result?.movieResponse?.totalPages ?? 0
            currentPage = result?.movieResponse?.page ?? 0
            let dataResult = result?.movieResponse?.movieList ?? []
            data.append(contentsOf: dataResult)
            listIsFull = currentPage==pageSize
        }
    }
}
